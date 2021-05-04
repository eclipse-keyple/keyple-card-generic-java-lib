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

import org.eclipse.keyple.core.card.CardApiProperties;
import org.eclipse.keyple.core.card.spi.CardExtensionSpi;
import org.eclipse.keyple.core.common.CommonsApiProperties;
import org.eclipse.keyple.core.service.Reader;
import org.eclipse.keyple.core.service.ServiceApiProperties;
import org.eclipse.keyple.core.service.selection.CardSelector;
import org.eclipse.keyple.core.service.selection.spi.CardSelection;
import org.eclipse.keyple.core.service.selection.spi.SmartCard;

/**
 * Implementation of {@link GenericExtensionService}.
 *
 * @since 2.0
 */
final class GenericExtensionServiceAdapter implements GenericExtensionService, CardExtensionSpi {

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
  public CardTransactionService createCardTransaction(Reader reader, SmartCard card) {
    return new CardTransactionServiceAdapter(reader, card);
  }

  @Override
  public CardResourceProfileExtension createCardResourceProfileExtension() {
    return new CardResourceProfileExtensionAdapter();
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
  public String getServiceApiVersion() {
    return ServiceApiProperties.VERSION;
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
