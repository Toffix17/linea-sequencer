/*
 * Copyright Consensys Software Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package net.consensys.linea.config;

import com.google.common.base.MoreObjects;
import picocli.CommandLine;

/** The Linea CLI options. */
public class LineaTransactionPoolValidatorCliOptions {

  public static final String DENY_LIST_PATH = "--plugin-linea-deny-list-path";
  public static final String DEFAULT_DENY_LIST_PATH = "lineaDenyList.txt";

  public static final String MAX_TX_GAS_LIMIT_OPTION = "--plugin-linea-max-tx-gas-limit";
  public static final int DEFAULT_MAX_TRANSACTION_GAS_LIMIT = 30_000_000;

  public static final String MAX_TX_CALLDATA_SIZE = "--plugin-linea-max-tx-calldata-size";
  public static final int DEFAULT_MAX_TX_CALLDATA_SIZE = 60_000;

  public static final String TX_POOL_ENABLE_SIMULATION_CHECK_API =
      "--plugin-linea-tx-pool-simulation-check-api-enabled";
  public static final boolean DEFAULT_TX_POOL_ENABLE_SIMULATION_CHECK_API = true;

  public static final String TX_POOL_ENABLE_SIMULATION_CHECK_P2P =
      "--plugin-linea-tx-pool-simulation-check-p2p-enabled";
  public static final boolean DEFAULT_TX_POOL_ENABLE_SIMULATION_CHECK_P2P = false;

  @CommandLine.Option(
      names = {DENY_LIST_PATH},
      hidden = true,
      paramLabel = "<STRING>",
      description =
          "Path to the file containing the deny list (default: " + DEFAULT_DENY_LIST_PATH + ")")
  private String denyListPath = DEFAULT_DENY_LIST_PATH;

  @CommandLine.Option(
      names = {MAX_TX_GAS_LIMIT_OPTION},
      hidden = true,
      paramLabel = "<INT>",
      description =
          "Maximum gas limit for a transaction (default: "
              + DEFAULT_MAX_TRANSACTION_GAS_LIMIT
              + ")")
  private int maxTxGasLimit = DEFAULT_MAX_TRANSACTION_GAS_LIMIT;

  @CommandLine.Option(
      names = {MAX_TX_CALLDATA_SIZE},
      hidden = true,
      paramLabel = "<INTEGER>",
      description =
          "Maximum size for the calldata of a Transaction (default: "
              + DEFAULT_MAX_TX_CALLDATA_SIZE
              + ")")
  private int maxTxCallDataSize = DEFAULT_MAX_TX_CALLDATA_SIZE;

  @CommandLine.Option(
      names = {TX_POOL_ENABLE_SIMULATION_CHECK_API},
      arity = "0..1",
      hidden = true,
      paramLabel = "<BOOLEAN>",
      description =
          "Enable the simulation check for txs received via API? (default: ${DEFAULT-VALUE})")
  private boolean txPoolSimulationCheckApiEnabled = DEFAULT_TX_POOL_ENABLE_SIMULATION_CHECK_API;

  @CommandLine.Option(
      names = {TX_POOL_ENABLE_SIMULATION_CHECK_P2P},
      arity = "0..1",
      hidden = true,
      paramLabel = "<BOOLEAN>",
      description =
          "Enable the simulation check for txs received via p2p? (default: ${DEFAULT-VALUE})")
  private boolean txPoolSimulationCheckP2pEnabled = DEFAULT_TX_POOL_ENABLE_SIMULATION_CHECK_P2P;

  private LineaTransactionPoolValidatorCliOptions() {}

  /**
   * Create Linea cli options.
   *
   * @return the Linea cli options
   */
  public static LineaTransactionPoolValidatorCliOptions create() {
    return new LineaTransactionPoolValidatorCliOptions();
  }

  /**
   * Cli options from config.
   *
   * @param config the config
   * @return the cli options
   */
  public static LineaTransactionPoolValidatorCliOptions fromConfig(
      final LineaTransactionPoolValidatorConfiguration config) {
    final LineaTransactionPoolValidatorCliOptions options = create();
    options.denyListPath = config.denyListPath();
    options.maxTxGasLimit = config.maxTxGasLimit();
    options.maxTxCallDataSize = config.maxTxCalldataSize();
    options.txPoolSimulationCheckApiEnabled = config.txPoolSimulationCheckApiEnabled();
    options.txPoolSimulationCheckP2pEnabled = config.txPoolSimulationCheckP2pEnabled();
    return options;
  }

  /**
   * To domain object Linea factory configuration.
   *
   * @return the Linea factory configuration
   */
  public LineaTransactionPoolValidatorConfiguration toDomainObject() {
    return new LineaTransactionPoolValidatorConfiguration(
        denyListPath,
        maxTxGasLimit,
        maxTxCallDataSize,
        txPoolSimulationCheckApiEnabled,
        txPoolSimulationCheckP2pEnabled);
  }

  @Override
  public String toString() {
    return MoreObjects.toStringHelper(this)
        .add(DENY_LIST_PATH, denyListPath)
        .add(MAX_TX_GAS_LIMIT_OPTION, maxTxGasLimit)
        .add(MAX_TX_CALLDATA_SIZE, maxTxCallDataSize)
        .add(TX_POOL_ENABLE_SIMULATION_CHECK_API, txPoolSimulationCheckApiEnabled)
        .add(TX_POOL_ENABLE_SIMULATION_CHECK_P2P, txPoolSimulationCheckP2pEnabled)
        .toString();
  }
}
