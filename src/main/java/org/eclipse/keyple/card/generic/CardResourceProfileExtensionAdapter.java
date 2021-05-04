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
import org.eclipse.keyple.core.card.ProxyReader;
import org.eclipse.keyple.core.card.spi.CardResourceProfileExtensionSpi;
import org.eclipse.keyple.core.card.spi.SmartCardSpi;
import org.eclipse.keyple.core.service.CardSelectionServiceFactory;
import org.eclipse.keyple.core.service.Reader;
import org.eclipse.keyple.core.service.selection.CardSelectionResult;
import org.eclipse.keyple.core.service.selection.CardSelectionService;
import org.eclipse.keyple.core.service.selection.CardSelector;
import org.eclipse.keyple.core.util.Assert;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * (package-private)<br>
 * Implementation of {@link CardResourceProfileExtension}.
 *
 * @since 2.0
 */
class CardResourceProfileExtensionAdapter
    implements CardResourceProfileExtension, CardResourceProfileExtensionSpi {

  private static final Logger logger =
      LoggerFactory.getLogger(CardResourceProfileExtensionAdapter.class);

  private String atrRegex;

  /**
   * (private)<br>
   * Constructor.<br>
   * Sets an always matching ATR regex by default.
   */
  CardResourceProfileExtensionAdapter() {
    this.atrRegex = ".*";
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public void setAtrRegex(String atrRegex) {
    Assert.getInstance().notEmpty(atrRegex, "atrRegex");
    try {
      Pattern.compile(atrRegex);
    } catch (PatternSyntaxException exception) {
      throw new IllegalArgumentException("Invalid regular expression: " + atrRegex);
    }
    this.atrRegex = atrRegex;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public SmartCardSpi matches(ProxyReader reader) {
    if (!((Reader) reader).isCardPresent()) {
      return null;
    }

    CardSelector cardSelector = CardSelector.builder().filterByAtr(atrRegex).build();

    CardSelectionService cardSelectionService = CardSelectionServiceFactory.getService();

    CardSelectionAdapter cardSelection = new CardSelectionAdapter(cardSelector);

    cardSelectionService.prepareSelection(cardSelection);

    CardSelectionResult cardSelectionResult = null;

    try {
      cardSelectionResult = cardSelectionService.processCardSelectionScenario((Reader) reader);
    } catch (Exception e) {
      logger.warn("An exception occurred while selecting the card: '{}'.", e.getMessage(), e);
    }

    if (cardSelectionResult != null && cardSelectionResult.hasActiveSelection()) {
      return (SmartCardSpi) cardSelectionResult.getActiveSmartCard();
    }

    return null;
  }
}
