/* **************************************************************************************
 * Copyright (c) 2026 Calypso Networks Association https://calypsonet.org/
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

import org.eclipse.keypop.genericcard.CardTransactionManager;
import org.eclipse.keypop.genericcard.GenericCardApiFactory;
import org.eclipse.keypop.genericcard.GenericCardSelectionExtension;
import org.eclipse.keypop.reader.CardReader;
import org.eclipse.keypop.reader.selection.spi.SmartCard;

/**
 * Adapter of {@link GenericCardApiFactory}.
 *
 * @since 4.0.0
 */
class GenericCardApiFactoryAdapter implements GenericCardApiFactory {

  /**
   * {@inheritDoc}
   *
   * @since 4.0.0
   */
  @Override
  public GenericCardSelectionExtension createGenericCardSelectionExtension() {
    return new GenericCardSelectionExtensionAdapter();
  }

  /**
   * {@inheritDoc}
   *
   * @since 4.0.0
   */
  @Override
  public CardTransactionManager createCardTransaction(CardReader cardReader, SmartCard card) {
    return new CardTransactionManagerAdapter(cardReader, card);
  }
}
