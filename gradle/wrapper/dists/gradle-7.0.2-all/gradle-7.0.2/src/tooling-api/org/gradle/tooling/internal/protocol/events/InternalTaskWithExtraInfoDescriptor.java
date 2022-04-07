/*
 * Copyright 2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.gradle.tooling.internal.protocol.events;

import java.util.Set;

/**
 * DO NOT CHANGE THIS INTERFACE. It is part of the cross-version protocol.
 *
 * @since 5.1
 */
public interface InternalTaskWithExtraInfoDescriptor extends InternalTaskDescriptor {

    /**
     * Returns the dependencies of the task.
     */
    Set<? extends InternalOperationDescriptor> getDependencies();

    /**
     * Returns the plugin that registered this task.
     */
    InternalPluginIdentifier getOriginPlugin();

}
