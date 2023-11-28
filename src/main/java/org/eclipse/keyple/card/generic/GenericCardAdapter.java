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

import org.eclipse.keypop.card.CardSelectionResponseApi;
import org.eclipse.keypop.card.spi.SmartCardSpi;
import org.eclipse.keypop.reader.selection.spi.IsoSmartCard;
import org.eclipse.keypop.reader.selection.spi.SmartCard;

/**
 * Implementation of a generic {@link SmartCard}.
 *
 * @since 2.0.0
 */
final class GenericCardAdapter implements IsoSmartCard, SmartCardSpi {

  private final byte[] selectApplicationResponse;
  private final String powerOnData;

  /**
   * Constructor
   *
   * <p>Gets ATR and FCI from the {@link CardSelectionResponseApi} if they exist (both are
   * optional).
   *
   * @param cardSelectionResponse The {@link CardSelectionResponseApi} from the selection process.
   */
  GenericCardAdapter(CardSelectionResponseApi cardSelectionResponse) {
    this.powerOnData = cardSelectionResponse.getPowerOnData();
    this.selectApplicationResponse =
        cardSelectionResponse.getSelectApplicationResponse() != null
            ? cardSelectionResponse.getSelectApplicationResponse().getApdu()
            : null;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0.0
   */
  @Override
  public String getPowerOnData() {
    return powerOnData;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0.0
   */
  @Override
  public byte[] getSelectApplicationResponse() {
    return selectApplicationResponse;
  }
}
