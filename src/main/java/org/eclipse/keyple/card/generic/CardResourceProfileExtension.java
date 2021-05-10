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

import org.eclipse.keyple.core.common.KeypleCardResourceProfileExtension;

/**
 * Generic Card Resource Profile Extension to target a card within the {@link
 * org.eclipse.keyple.core.service.resource.CardResourceService}.
 *
 * @since 2.0
 */
public interface CardResourceProfileExtension extends KeypleCardResourceProfileExtension {

  /**
   * Sets a regular expression aimed to be applied to the card's ATR in order to identify it.
   *
   * @param atrRegex A not empty string.
   * @return The object instance.
   * @throws IllegalArgumentException If the provided regular expression is null or invalid.
   * @since 2.0
   */
  CardResourceProfileExtension setAtrRegex(String atrRegex);
}
