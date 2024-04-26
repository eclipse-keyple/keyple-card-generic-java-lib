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

import java.util.HashSet;
import java.util.Set;
import org.eclipse.keypop.card.CardSelectionResponseApi;
import org.eclipse.keypop.card.spi.*;

/**
 * Implementation of {@link GenericCardSelectionExtension}.
 *
 * @since 2.0.0
 */
class GenericCardSelectionExtensionAdapter
    implements GenericCardSelectionExtension, CardSelectionExtensionSpi {
  private static final int DEFAULT_SUCCESSFUL_CODE = 0x9000;
  private final Set<Integer> successfulSelectionStatusWords;

  /**
   * Creates an instance.
   *
   * @since 2.0.0
   */
  GenericCardSelectionExtensionAdapter() {
    successfulSelectionStatusWords = new HashSet<>(1);
    successfulSelectionStatusWords.add(DEFAULT_SUCCESSFUL_CODE);
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0.0
   */
  @Override
  public CardSelectionRequestSpi getCardSelectionRequest() {
    return new GenericCardSelectionRequestAdapter(successfulSelectionStatusWords);
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0.0
   */
  @Override
  public SmartCardSpi parse(CardSelectionResponseApi cardSelectionResponse) {
    return new GenericCardAdapter(cardSelectionResponse);
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0.0
   */
  @Override
  public GenericCardSelectionExtension addSuccessfulStatusWord(int statusWord) {
    successfulSelectionStatusWords.add(statusWord);
    return this;
  }
}
