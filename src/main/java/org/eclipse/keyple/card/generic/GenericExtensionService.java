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

import org.eclipse.keyple.core.common.CommonApiProperties;
import org.eclipse.keyple.core.common.KeypleCardExtension;
import org.eclipse.keyple.core.service.resource.spi.CardResourceProfileExtension;
import org.eclipse.keypop.card.CardApiProperties;
import org.eclipse.keypop.reader.CardReader;
import org.eclipse.keypop.reader.ReaderApiProperties;
import org.eclipse.keypop.reader.selection.CardSelector;
import org.eclipse.keypop.reader.selection.IsoCardSelector;
import org.eclipse.keypop.reader.selection.spi.CardSelectionExtension;
import org.eclipse.keypop.reader.selection.spi.SmartCard;

/**
 * Card extension service providing basic access to APDU exchange functions with a card.
 *
 * @since 2.0.0
 */
public final class GenericExtensionService implements KeypleCardExtension {

  private static final GenericExtensionService INSTANCE = new GenericExtensionService();

  /** Constructor */
  private GenericExtensionService() {}

  /**
   * Gets the unique instance of this object.
   *
   * @return A not null reference.
   */
  public static GenericExtensionService getInstance() {
    return INSTANCE;
  }

  /**
   * Creates an instance of {@link CardSelectionExtension}.
   *
   * @return A not null reference.
   * @since 2.0.0
   */
  public GenericCardSelectionExtension createGenericCardSelectionExtension() {
    return new GenericCardSelectionExtensionAdapter();
  }

  /**
   * Creates an instance of {@link CardTransactionManager}.
   *
   * @param reader The reader through which the card communicates.
   * @param card The initial card data provided by the selection process.
   * @return A not null reference.
   * @since 2.0.0
   */
  public CardTransactionManager createCardTransaction(CardReader reader, SmartCard card) {
    return new CardTransactionManagerAdapter(reader, card);
  }

  /**
   * Creates an instance of {@link CardResourceProfileExtension} to be provided to the {@link
   * org.eclipse.keyple.core.service.resource.CardResourceService}.
   *
   * <p>The provided argument defines the selection rules to be applied to the card when detected by
   * the card resource service.
   *
   * @param cardSelector The ISO card selector targeting the expected card profile.
   * @param genericCardSelectionExtension A not null generic card extension.
   * @return A not null reference.
   * @throws IllegalArgumentException If genericCardSelectionExtension is null.
   * @since 2.0.0
   */
  public CardResourceProfileExtension createCardResourceProfileExtension(
      IsoCardSelector cardSelector,
      GenericCardSelectionExtension genericCardSelectionExtension) {
    return new GenericCardResourceProfileExtensionAdapter(
        cardSelector, genericCardSelectionExtension);
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0.0
   */
  @Override
  public String getCardApiVersion() {
    return CardApiProperties.VERSION;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0.0
   */
  @Override
  public String getReaderApiVersion() {
    return ReaderApiProperties.VERSION;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0.0
   */
  @Override
  public String getCommonApiVersion() {
    return CommonApiProperties.VERSION;
  }
}
