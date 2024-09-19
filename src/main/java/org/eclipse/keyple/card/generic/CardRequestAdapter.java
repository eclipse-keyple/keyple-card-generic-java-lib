/* **************************************************************************************
 * Copyright (c) 2018 Calypso Networks Association https://calypsonet.org/
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

import java.util.List;
import org.eclipse.keypop.card.spi.ApduRequestSpi;
import org.eclipse.keypop.card.spi.CardRequestSpi;

/**
 * This POJO contains an ordered list of {@link ApduRequestSpi} and the associated status word check
 * policy.
 *
 * @since 2.0.0
 */
final class CardRequestAdapter implements CardRequestSpi {

  private final List<ApduRequestSpi> apduRequests;
  private final boolean stopOnUnsuccessfulStatusWord;
  private final boolean isStatusCodesVerificationEnabled; // Only for JSON backward compatibility

  /**
   * Builds a card request with a list of {@link ApduRequestSpi } and the flag indicating the
   * expected response checking behavior.
   *
   * <p>When the status word verification is enabled, the transmission of the APDUs must be
   * interrupted as soon as the status word of a response is unexpected.
   *
   * @param apduRequests A not empty list.
   * @param stopOnUnsuccessfulStatusWord True if the transmission must be stopped when an unexpected
   *     status word is received.
   * @since 2.0.0
   */
  CardRequestAdapter(List<ApduRequestSpi> apduRequests, boolean stopOnUnsuccessfulStatusWord) {
    this.apduRequests = apduRequests;
    this.stopOnUnsuccessfulStatusWord = stopOnUnsuccessfulStatusWord;
    this.isStatusCodesVerificationEnabled = stopOnUnsuccessfulStatusWord;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0.0
   */
  @Override
  public List<ApduRequestSpi> getApduRequests() {
    return apduRequests;
  }

  /**
   * {@inheritDoc}
   *
   * @since 2.0.0
   */
  @Override
  public boolean stopOnUnsuccessfulStatusWord() {
    return stopOnUnsuccessfulStatusWord;
  }
}
