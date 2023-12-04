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

import org.eclipse.keyple.core.service.resource.spi.CardResourceProfileExtension;
import org.eclipse.keyple.core.util.Assert;
import org.eclipse.keypop.reader.CardReader;
import org.eclipse.keypop.reader.ReaderApiFactory;
import org.eclipse.keypop.reader.selection.CardSelectionManager;
import org.eclipse.keypop.reader.selection.CardSelectionResult;
import org.eclipse.keypop.reader.selection.CardSelector;
import org.eclipse.keypop.reader.selection.IsoCardSelector;
import org.eclipse.keypop.reader.selection.spi.SmartCard;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of {@link CardResourceProfileExtension} that provides the capability to filter
 * cards of any type.
 *
 * @since 2.0.0
 */
class GenericCardResourceProfileExtensionAdapter implements CardResourceProfileExtension {
  private static final Logger logger =
      LoggerFactory.getLogger(GenericCardResourceProfileExtensionAdapter.class);
  private final GenericCardSelectionExtensionAdapter genericCardSelection;
  private final IsoCardSelector cardSelector;

  /**
   * @param cardSelector An ISO card selector.
   * @param genericCardSelectionExtension The generic card selection extension.
   * @since 2.0.0
   */
  GenericCardResourceProfileExtensionAdapter(
      IsoCardSelector cardSelector,
      GenericCardSelectionExtension genericCardSelectionExtension) {

    Assert.getInstance().notNull(genericCardSelectionExtension, "genericCardSelectionExtension");

    this.cardSelector = cardSelector;
    this.genericCardSelection =
        (GenericCardSelectionExtensionAdapter) genericCardSelectionExtension;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0.0
   */
  @Override
  public SmartCard matches(CardReader reader, ReaderApiFactory readerApiFactory) {

    if (!reader.isCardPresent()) {
      return null;
    }
    CardSelectionManager genericCardSelectionManager =
        readerApiFactory.createCardSelectionManager();
    genericCardSelectionManager.prepareSelection(cardSelector, genericCardSelection);
    CardSelectionResult genericCardSelectionResult = null;
    try {
      genericCardSelectionResult = genericCardSelectionManager.processCardSelectionScenario(reader);
    } catch (Exception e) {
      logger.warn("An exception occurred while selecting the card: '{}'.", e.getMessage(), e);
    }

    if (genericCardSelectionResult != null) {
      return genericCardSelectionResult.getActiveSmartCard();
    }

    return null;
  }
}
