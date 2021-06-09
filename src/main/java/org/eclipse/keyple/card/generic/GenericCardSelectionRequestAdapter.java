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

import org.calypsonet.terminal.card.spi.CardRequestSpi;
import org.calypsonet.terminal.card.spi.CardSelectionRequestSpi;
import org.calypsonet.terminal.card.spi.CardSelectorSpi;

/**
 * (package-private)<br>
 * Implementation of {@link CardSelectionRequestSpi}
 *
 * @since 2.0
 */
final class GenericCardSelectionRequestAdapter implements CardSelectionRequestSpi {
  private final CardSelectorSpi cardSelector;
  private final CardRequestSpi cardRequest;

  /**
   * Builds a card selection request to open a logical channel with additional APDUs to be sent
   * after the selection step.
   *
   * @param cardSelector The card selector.
   * @param cardRequest The card request.
   * @since 2.0
   */
  GenericCardSelectionRequestAdapter(CardSelectorSpi cardSelector, CardRequestSpi cardRequest) {
    this.cardSelector = cardSelector;
    this.cardRequest = cardRequest;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  public CardSelectorSpi getCardSelector() {
    return cardSelector;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  public CardRequestSpi getCardRequest() {
    return cardRequest;
  }
}
