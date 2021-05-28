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
import org.calypsonet.terminal.card.spi.SmartCardSpi;
import org.calypsonet.terminal.reader.selection.spi.SmartCard;
import org.eclipse.keyple.core.util.json.JsonUtil;

/** Implementation of a generic {@link SmartCard}. */
class GenericCardAdapter implements SmartCard, SmartCardSpi {
  private final byte[] fciBytes;
  private final byte[] powerOnData;

  /**
   * Creates an instance.
   *
   * <p>Gets ATR and FCI from the {@link CardSelectionResponseApi} if they exist (both are
   * optional).
   *
   * @param cardSelectionResponse The {@link CardSelectionResponseApi} from the selection process.
   */
  GenericCardAdapter(CardSelectionResponseApi cardSelectionResponse) {
    if (cardSelectionResponse.getSelectionStatus().getPowerOnData() != null) {
      this.powerOnData = cardSelectionResponse.getSelectionStatus().getPowerOnData();
    } else {
      this.powerOnData = null;
    }
    if (cardSelectionResponse.getSelectionStatus().getFci() != null) {
      this.fciBytes = cardSelectionResponse.getSelectionStatus().getFci().getBytes();
    } else {
      this.fciBytes = null;
    }
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public boolean hasFci() {
    return fciBytes != null;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public byte[] getFciBytes() {
    return fciBytes;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public boolean hasPowerOnData() {
    return powerOnData != null;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0
   */
  @Override
  public byte[] getPowerOnData() {
    return powerOnData;
  }

  /**
   * Converts the content of this object into a String using the Json format.
   *
   * @since 2.0
   */
  @Override
  public String toString() {
    return JsonUtil.toJson(this);
  }
}
