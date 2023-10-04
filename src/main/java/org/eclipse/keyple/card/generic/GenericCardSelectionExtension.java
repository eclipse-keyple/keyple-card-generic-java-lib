/* **************************************************************************************
 * Copyright (c) 2023 Calypso Networks Association https://calypsonet.org/
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

import org.eclipse.keypop.reader.selection.spi.CardSelectionExtension;

/**
 * Card specific {@link CardSelectionExtension} providing means to add successful status word.
 *
 * @since 3.0.0
 */
public interface GenericCardSelectionExtension extends CardSelectionExtension {

  /**
   * Adds a status word to the list of those that should be considered successful for the Select
   * Application APDU.
   *
   * <p>Note: initially, the list contains the standard successful status word {@code 9000h}.
   *
   * @param statusWord A positive int &le; {@code FFFFh}.
   * @return The current instance.
   * @since 2.0.0
   */
  GenericCardSelectionExtension addSuccessfulStatusWord(int statusWord);
}
