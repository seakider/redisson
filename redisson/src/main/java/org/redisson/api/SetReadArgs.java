/**
 * Copyright (c) 2013-2024 Nikita Koksharov
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.redisson.api;
/**
 *
 * @author seakider
 *
 */

import org.redisson.api.RScoredSortedSet.Aggregate;

public interface SetReadArgs {

    /**
     * Defines a weight multiplier for each ScoredSortedSet
     *
     * @param weights weight multiplier
     * @return arguments object
     */
    SetReadArgs weights(Double... weights);

    /**
     * Defines aggregation method
     *
     * @param aggregate score aggregation mode
     * @return arguments object
     */
    SetReadArgs aggregate(Aggregate aggregate);

}
