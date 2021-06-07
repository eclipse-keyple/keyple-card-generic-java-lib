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
import org.calypsonet.terminal.reader.selection.spi.SmartCard;
import org.eclipse.keyple.core.common.CommonsApiProperties;
import org.eclipse.keyple.core.common.KeypleCardExtension;

/**
 * Card extension service providing basic access to APDU exchange functions with a card.
 *
 * @since 2.0
 */
public final class GenericExtensionService implements KeypleCardExtension {

  private static final GenericExtensionService instance = new GenericExtensionService();

  /** Constructor */
  private GenericExtensionService() {}

  /**
   * Gets the unique instance of this object.
   *
   * @return A not null reference.
   */
  public static GenericExtensionService getInstance() {
    return instance;
  }

  /**
   * Creates an instance of {@link GenericCardSelection}.
   *
   * @return A not null reference.
   * @since 2.0
   */
  public GenericCardSelection createCardSelection() {
    return new GenericCardSelectionAdapter();
  }

  /**
   * Creates an instance of {@link CardTransactionService}.
   *
   * @param reader The reader through which the card communicates.
   * @param card The initial card data provided by the selection process.
   * @return A not null reference.
   * @since 2.0
   */
  public CardTransactionService createCardTransaction(CardReader reader, SmartCard card) {
    return new CardTransactionServiceAdapter(reader, card);
  }

  /**
   * Creates an instance of {@link GenericCardResourceProfileExtension} to be provided to the {@link
   * org.eclipse.keyple.core.service.resource.CardResourceService}.
   *
   * @return A not null reference.
   * @since 2.0
   */
  public GenericCardResourceProfileExtension createCardResourceProfileExtension() {
    return new GenericCardResourceProfileExtension();
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
