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

import org.calypsonet.terminal.card.CardSelectionResponseApi;
import org.calypsonet.terminal.card.spi.CardSelectionRequestSpi;
import org.calypsonet.terminal.card.spi.CardSelectionSpi;
import org.calypsonet.terminal.card.spi.CardSelectorSpi;
import org.calypsonet.terminal.card.spi.SmartCardSpi;
import org.calypsonet.terminal.reader.selection.spi.CardSelection;
import org.calypsonet.terminal.reader.selection.spi.CardSelector;

/**
 * Implementation of a generic {@link CardSelection}.
 *
 * @since 2.0
 */
class CardSelectionAdapter implements CardSelection, CardSelectionSpi {

  /** (private) */
  private final CardSelector cardSelector;

  /**
   * (package-private) Creates an instance.
   *
   * @param cardSelector A card selector.
   */
  CardSelectionAdapter(CardSelector cardSelector) {
    this.cardSelector = cardSelector;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public CardSelector getCardSelector() {
    return cardSelector;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public CardSelectionRequestSpi getCardSelectionRequest() {
    return new GenericCardSelectionRequestAdapter((CardSelectorSpi) cardSelector, null);
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public SmartCardSpi parse(CardSelectionResponseApi cardSelectionResponse) {
    return new GenericCardAdapter(cardSelectionResponse);
  }
}
