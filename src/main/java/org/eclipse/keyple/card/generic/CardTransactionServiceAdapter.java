/* **************************************************************************************
 * Copyright (c) 2021 Calypso Networks Association https://www.calypsonet-asso.org/
 *
 * See the NOTICE file(s) distributed with this work for additional information
 * regarding copyright ownership.
 *
 * This program and the accompanying materials are made available under the terms of the
 * Eclipse Public License 2.0 which is available at http://www.eclipse.org/legal/epl-2.0
 *
 * SPDX-License-Identifier: EPL-2.0
 ************************************************************************************** */
package org.eclipse.keyple.card.generic;

import java.util.ArrayList;
import java.util.List;
import org.eclipse.keyple.core.card.*;
import org.eclipse.keyple.core.service.Reader;
import org.eclipse.keyple.core.service.selection.spi.SmartCard;
import org.eclipse.keyple.core.util.ApduUtil;
import org.eclipse.keyple.core.util.Assert;
import org.eclipse.keyple.core.util.ByteArrayUtil;

/**
 * (package-private)<br>
 * Implementation of {@link CardTransactionService}.
 *
 * @since 2.0
 */
class CardTransactionServiceAdapter implements CardTransactionService {
  private final Reader reader;
  private final List<ApduRequest> apduRequests;
  private ChannelControl channelControl;

  /**
   * (package-private)<br>
   * Creates an instance of {@link CardTransactionService}.
   *
   * @param reader The reader through which the card communicates.
   * @param card The initial card data provided by the selection process.
   * @throws IllegalArgumentException If the card resource or one of its components is null.
   * @since 2.0
   */
  CardTransactionServiceAdapter(Reader reader, SmartCard card) {
    Assert.getInstance().notNull(reader, "reader").notNull(card, "card");
    this.reader = reader;
    apduRequests = new ArrayList<ApduRequest>();
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public CardTransactionService prepareApdu(String apduCommand) {
    Assert.getInstance()
        .notEmpty(apduCommand, "apduCommand")
        .isTrue(ByteArrayUtil.isValidHexString(apduCommand), "apduCommand");
    prepareApdu(ByteArrayUtil.fromHex(apduCommand));
    return this;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public CardTransactionService prepareApdu(byte[] apduCommand) {
    Assert.getInstance()
        .notNull(apduCommand, "apduCommand")
        .isInRange(apduCommand.length, 5, 251, "length");
    apduRequests.add(new ApduRequest(apduCommand));
    return this;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public CardTransactionService prepareApdu(
      byte cla, byte ins, byte p1, byte p2, byte[] dataIn, Byte le) {
    apduRequests.add(new ApduRequest(ApduUtil.build(cla, ins, p1, p2, dataIn, le)));
    return this;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public CardTransactionService prepareReleaseChannel() {
    channelControl = ChannelControl.CLOSE_AFTER;
    return this;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public List<byte[]> processApdusToByteArrays() throws TransactionException {
    CardResponse cardResponse;
    if (apduRequests.isEmpty()) {
      return new ArrayList<byte[]>(0);
    }
    try {
      cardResponse =
          ((ProxyReader) reader)
              .transmitCardRequest(new CardRequest(apduRequests, false), channelControl);
    } catch (ReaderCommunicationException e) {
      throw new TransactionException("Reader communication error.", e);
    } catch (CardCommunicationException e) {
      throw new TransactionException("Card communication error.", e);
    } catch (UnexpectedStatusCodeException e) {
      throw new TransactionException("Apdu error.", e);
    } finally {
      apduRequests.clear();
    }
    List<byte[]> apduResponsesBytes = new ArrayList<byte[]>();
    for (ApduResponse apduResponse : cardResponse.getApduResponses()) {
      apduResponsesBytes.add(apduResponse.getBytes());
    }
    return apduResponsesBytes;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public List<String> processApdusToHexStrings() throws TransactionException {
    List<byte[]> apduResponsesBytes = processApdusToByteArrays();
    List<String> apduResponsesHex = new ArrayList<String>();
    for (byte[] bytes : apduResponsesBytes) {
      apduResponsesHex.add(ByteArrayUtil.toHex(bytes));
    }
    return apduResponsesHex;
  }
}
