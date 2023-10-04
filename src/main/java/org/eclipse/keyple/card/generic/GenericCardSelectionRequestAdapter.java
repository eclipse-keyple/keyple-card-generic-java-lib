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

import java.util.Set;
import org.eclipse.keypop.card.spi.CardRequestSpi;
import org.eclipse.keypop.card.spi.CardSelectionRequestSpi;

/**
 * Implementation of {@link CardSelectionRequestSpi}
 *
 * @since 2.0.0
 */
final class GenericCardSelectionRequestAdapter implements CardSelectionRequestSpi {
  private final Set<Integer> successfulSelectionStatusWords;

  /**
   * Builds a card selection request to open a logical channel with additional APDUs to be sent
   * after the selection step.
   *
   * @since 2.0.0
   */
  GenericCardSelectionRequestAdapter(Set<Integer> successfulSelectionStatusWords) {
    this.successfulSelectionStatusWords = successfulSelectionStatusWords;
  }

  /**
   * {@inheritDoc}
   *
   * @since 3.0.0
   */
  @Override
  public Set<Integer> getSuccessfulSelectionStatusWords() {
    return successfulSelectionStatusWords;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0.0
   */
  @Override
  public CardRequestSpi getCardRequest() {
    // no additional command following the card selection in this extension
    return null;
  }
}
