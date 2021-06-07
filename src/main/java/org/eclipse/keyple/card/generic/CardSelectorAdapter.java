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

import java.util.LinkedHashSet;
import java.util.Set;
import org.calypsonet.terminal.card.spi.CardSelectorSpi;
import org.eclipse.keyple.core.util.Assert;
import org.eclipse.keyple.core.util.ByteArrayUtil;
import org.eclipse.keyple.core.util.json.JsonUtil;

/**
 * (package-private)<br>
 * Implementation of {@link CardSelectorSpi}.
 *
 * @since 2.0
 */
final class CardSelectorAdapter implements CardSelectorSpi {

  private static final int AID_MIN_LENGTH = 5;
  private static final int AID_MAX_LENGTH = 16;
  private static final int DEFAULT_SUCCESSFUL_CODE = 0x9000;

  private String cardProtocol;
  private String powerOnDataRegex;
  private byte[] aid;
  private FileOccurrence fileOccurrence;
  private FileControlInformation fileControlInformation;
  private final Set<Integer> successfulSelectionStatusWords;

  /**
   * (package-private)<br>
   * Created an instance of {@link CardSelectorAdapter}.
   *
   * <p>Initialize default values.
   *
   * @since 2.0
   */
  CardSelectorAdapter() {
    fileOccurrence = FileOccurrence.FIRST;
    fileControlInformation = FileControlInformation.FCI;
    successfulSelectionStatusWords = new LinkedHashSet<Integer>();
    successfulSelectionStatusWords.add(DEFAULT_SUCCESSFUL_CODE);
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  public CardSelectorSpi filterByCardProtocol(String cardProtocol) {

    Assert.getInstance().notEmpty(cardProtocol, "cardProtocol");

    if (this.cardProtocol != null) {
      throw new IllegalStateException(
          String.format("cardProtocol has already been set to '%s'", this.cardProtocol));
    }

    this.cardProtocol = cardProtocol;
    return this;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  public CardSelectorSpi filterByPowerOnData(String powerOnDataRegex) {

    Assert.getInstance().notEmpty(powerOnDataRegex, "powerOnDataRegex");

    if (this.powerOnDataRegex != null) {
      throw new IllegalStateException(
          String.format("powerOnDataRegex has already been set to '%s'", this.powerOnDataRegex));
    }

    this.powerOnDataRegex = powerOnDataRegex;
    return this;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  public CardSelectorSpi filterByDfName(byte[] aid) {

    Assert.getInstance()
        .notNull(aid, "aid")
        .isInRange(aid.length, AID_MIN_LENGTH, AID_MAX_LENGTH, "aid");

    if (this.aid != null) {
      throw new IllegalStateException(
          String.format("aid has already been set to '%s'", ByteArrayUtil.toHex(this.aid)));
    }

    this.aid = aid;
    return this;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  public CardSelectorSpi filterByDfName(String aid) {
    return filterByDfName(ByteArrayUtil.fromHex(aid));
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  public CardSelectorSpi setFileOccurrence(FileOccurrence fileOccurrence) {
    Assert.getInstance().notNull(fileOccurrence, "fileOccurrence");
    if (this.fileOccurrence != null) {
      throw new IllegalStateException(
          String.format("fileOccurrence has already been set to '%s'", this.fileOccurrence));
    }
    this.fileOccurrence = fileOccurrence;
    return this;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  public CardSelectorSpi setFileControlInformation(FileControlInformation fileControlInformation) {
    Assert.getInstance().notNull(fileControlInformation, "fileControlInformation");
    if (this.fileControlInformation != null) {
      throw new IllegalStateException(
          String.format(
              "fileControlInformation has already been set to '%s'", this.fileControlInformation));
    }
    this.fileControlInformation = fileControlInformation;
    return this;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  public CardSelectorSpi addSuccessfulStatusWord(int statusWord) {
    this.successfulSelectionStatusWords.add(statusWord);
    return this;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public final String getCardProtocol() {
    return cardProtocol;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public String getPowerOnDataRegex() {
    return powerOnDataRegex;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public byte[] getAid() {
    return aid;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public FileOccurrence getFileOccurrence() {
    return fileOccurrence;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public FileControlInformation getFileControlInformation() {
    return fileControlInformation;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public Set<Integer> getSuccessfulSelectionStatusWords() {
    return successfulSelectionStatusWords;
  }

  @Override
  public String toString() {
    return "CARD_SELECTOR = " + JsonUtil.toJson(this);
  }
}
