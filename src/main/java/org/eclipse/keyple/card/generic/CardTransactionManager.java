/* **************************************************************************************
 * Copyright (c) 2021 Calypso Networks Association https://calypsonet.org/
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

import java.util.List;

/** Provides basic methods to prepare and process APDU exchange with a card. */
public interface CardTransactionManager {

  /**
   * Prepares an APDU to be transmitted the next time {@link
   * #processApdusToByteArrays(ChannelControl)} is invoked.
   *
   * @param apduCommand A not empty hexadecimal string containing a raw APDU command.
   * @return This instance.
   * @since 2.0.0
   */
  CardTransactionManager prepareApdu(String apduCommand);

  /**
   * Prepares an APDU to be transmitted the next time {@link
   * #processApdusToByteArrays(ChannelControl)} is invoked.
   *
   * @param apduCommand A not empty byte arrays containing raw APDU commands.
   * @return This instance.
   * @since 2.0.0
   */
  CardTransactionManager prepareApdu(byte[] apduCommand);

  /**
   * Prepares an APDU to be transmitted the next time {@link
   * #processApdusToByteArrays(ChannelControl)} is invoked.
   *
   * @param cla The class byte.
   * @param ins The instruction byte.
   * @param p1 The P1 parameter.
   * @param p2 The P2 parameter.
   * @param dataIn The APDU data, null if there is no data.
   * @param le The expected output length, 0 if the output length is unspecified, null if no output
   *     data is expected.
   * @return This instance.
   * @since 2.0.0
   */
  CardTransactionManager prepareApdu(byte cla, byte ins, byte p1, byte p2, byte[] dataIn, Byte le);

  /**
   * Transmits all prepared APDUs, closes the physical channel if required, and returns a list of
   * responses to the APDUs in the form of list of of byte arrays.
   *
   * <p>If the prepared APDU list is empty an empty list is returned.
   *
   * <p>The prepared APDU list is cleared after this method has been invoked.
   *
   * @param channelControl Policy for managing the physical channel after executing commands to the
   *     card.
   * @return A not null reference.
   * @throws TransactionException If the communication with the card or the reader has failed.
   */
  List<byte[]> processApdusToByteArrays(ChannelControl channelControl) throws TransactionException;

  /**
   * Transmits all prepared APDUs, closes the physical channel if required, and returns a list of
   * responses to the APDUs in the form of list of hexadecimal strings.
   *
   * <p>If the prepared APDU list is empty an empty list is returned.
   *
   * <p>The prepared APDU list is cleared after this method has been invoked.
   *
   * @param channelControl Policy for managing the physical channel after executing commands to the
   *     card.
   * @return A not null reference.
   * @throws TransactionException If the communication with the card or the reader has failed.
   */
  List<String> processApdusToHexStrings(ChannelControl channelControl) throws TransactionException;
}
