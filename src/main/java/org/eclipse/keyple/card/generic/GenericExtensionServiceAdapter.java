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

import org.calypsonet.terminal.card.CardApiProperties;
import org.calypsonet.terminal.reader.CardReader;
import org.calypsonet.terminal.reader.ReaderApiProperties;
import org.calypsonet.terminal.reader.selection.spi.CardSelection;
import org.calypsonet.terminal.reader.selection.spi.CardSelector;
import org.calypsonet.terminal.reader.selection.spi.SmartCard;
import org.eclipse.keyple.core.common.CommonsApiProperties;

/**
 * Implementation of {@link GenericExtensionService}.
 *
 * @since 2.0
 */
final class GenericExtensionServiceAdapter implements GenericExtensionService {

  private static final GenericExtensionServiceAdapter instance =
      new GenericExtensionServiceAdapter();

  /** (package-private)<br> */
  GenericExtensionServiceAdapter() {}

  /**
   * (package-private)<br>
   * Gets the unique instance of this object.
   *
   * @return A not null reference.
   */
  static GenericExtensionService getInstance() {
    return instance;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public CardSelection createCardSelection(CardSelector cardSelector) {
    return new CardSelectionAdapter(cardSelector);
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public CardTransactionService createCardTransaction(CardReader reader, SmartCard card) {
    return new CardTransactionServiceAdapter(reader, card);
  }

  @Override
  public CardResourceProfileExtension createCardResourceProfileExtension() {
    return new CardResourceProfileExtension();
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public String getCardApiVersion() {
    return CardApiProperties.VERSION;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public String getReaderApiVersion() {
    return ReaderApiProperties.VERSION;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public String getCommonsApiVersion() {
    return CommonsApiProperties.VERSION;
  }
}
