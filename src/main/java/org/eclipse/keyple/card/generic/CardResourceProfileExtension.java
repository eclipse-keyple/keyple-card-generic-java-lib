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

import java.util.regex.Pattern;
import java.util.regex.PatternSyntaxException;
import org.calypsonet.terminal.reader.CardReader;
import org.calypsonet.terminal.reader.selection.CardSelectionResult;
import org.calypsonet.terminal.reader.selection.CardSelectionService;
import org.calypsonet.terminal.reader.selection.spi.CardSelector;
import org.calypsonet.terminal.reader.selection.spi.SmartCard;
import org.eclipse.keyple.core.service.resource.spi.CardResourceProfileExtensionSpi;
import org.eclipse.keyple.core.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Implementation of {@link CardResourceProfileExtensionSpi}.
 *
 * @since 2.0
 */
public class CardResourceProfileExtension implements CardResourceProfileExtensionSpi {

  private static final Logger logger = LoggerFactory.getLogger(CardResourceProfileExtension.class);

  private String powerOnDataRegex;

  /**
   * (private)<br>
   * Constructor.<br>
   * Sets an always matching power-on data regex by default.
   */
  CardResourceProfileExtension() {
    this.powerOnDataRegex = ".*";
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  public CardResourceProfileExtension setPowerOnDataRegex(String powerOnDataRegex) {
    Assert.getInstance().notEmpty(powerOnDataRegex, "powerOnDataRegex");

    try {
      Pattern.compile(powerOnDataRegex);
    } catch (PatternSyntaxException exception) {
      throw new IllegalArgumentException("Invalid regular expression: " + powerOnDataRegex);
    }
    this.powerOnDataRegex = powerOnDataRegex;

    return this;
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

    CardSelector cardSelector =
        GenericExtensionService.getInstance()
            .createCardSelector()
            .filterByPowerOnData(powerOnDataRegex);

    CardSelectionAdapter cardSelection = new CardSelectionAdapter(cardSelector);

    cardSelectionService.prepareSelection(cardSelection);

    CardSelectionResult cardSelectionResult = null;

    try {
      cardSelectionResult = cardSelectionService.processCardSelectionScenario(reader);
    } catch (Exception e) {
      logger.warn("An exception occurred while selecting the card: '{}'.", e.getMessage(), e);
    }

    if (cardSelectionResult != null && cardSelectionResult.hasActiveSelection()) {
      return cardSelectionResult.getActiveSmartCard();
    }

    return null;
  }
}
