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

import org.calypsonet.terminal.reader.CardReader;
import org.calypsonet.terminal.reader.selection.CardSelectionResult;
import org.calypsonet.terminal.reader.selection.CardSelectionService;
import org.calypsonet.terminal.reader.selection.spi.SmartCard;
import org.eclipse.keyple.core.service.resource.spi.CardResourceProfileExtension;
import org.eclipse.keyple.core.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * (package-private)<br>
 * Implementation of {@link CardResourceProfileExtension} dedicated to card identification.
 *
 * @since 2.0
 */
class GenericCardResourceProfileExtensionAdapter implements CardResourceProfileExtension {
  private static final Logger logger =
      LoggerFactory.getLogger(GenericCardResourceProfileExtensionAdapter.class);
  private final GenericCardSelection genericCardSelection;

  /**
   * (package-private)<br>
   *
   * @param genericCardSelection The {@link GenericCardSelection}.
   * @since 2.0
   */
  GenericCardResourceProfileExtensionAdapter(GenericCardSelection genericCardSelection) {

    Assert.getInstance().notNull(genericCardSelection, "genericCardSelection");

    this.genericCardSelection = genericCardSelection;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public SmartCard matches(CardReader reader, CardSelectionService cardSelectionService) {

    if (!reader.isCardPresent()) {
      return null;
    }

    cardSelectionService.prepareSelection(genericCardSelection);
    CardSelectionResult cardSelectionResult = null;
    try {
      cardSelectionResult = cardSelectionService.processCardSelectionScenario(reader);
    } catch (Exception e) {
      logger.warn("An exception occurred while selecting the card: '{}'.", e.getMessage(), e);
    }

    if (cardSelectionResult != null) {
      return cardSelectionResult.getActiveSmartCard();
    }

    return null;
  }
}
