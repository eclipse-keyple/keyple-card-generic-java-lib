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

import org.eclipse.keyple.core.common.KeypleCardExtension;
import org.eclipse.keyple.core.service.Reader;
import org.eclipse.keyple.core.service.selection.CardSelector;
import org.eclipse.keyple.core.service.selection.spi.CardSelection;
import org.eclipse.keyple.core.service.selection.spi.SmartCard;

/**
 * Card extension service providing basic access to APDU exchange functions with a card.
 *
 * @since 2.0
 */
public interface GenericExtensionService extends KeypleCardExtension {

  /**
   * Creates an instance of {@link CardSelection}.
   *
   * @param cardSelector A card selector.
   * @return A not null reference.
   * @since 2.0
   */
  CardSelection createCardSelection(CardSelector cardSelector);

  /**
   * Creates an instance of {@link CardTransactionService}.
   *
   * @param reader The reader through which the card communicates.
   * @param card The initial card data provided by the selection process.
   * @return A not null reference.
   * @since 2.0
   */
  CardTransactionService createCardTransaction(Reader reader, SmartCard card);

  /**
   * Creates an instance of {@link CardResourceProfileExtension} to be provided to the {@link
   * org.eclipse.keyple.core.service.resource.CardResourceService}.
   *
   * @return A not null reference.
   * @since 2.0
   */
  CardResourceProfileExtension createCardResourceProfileExtension();
}
