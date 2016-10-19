/*
 *    Copyright 2016 Thomas Heinemann
 *
 *  Licensed under the Apache License, Version 2.0 (the "License");
 *  you may not use this file except in compliance with the License.
 *  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *  Unless required by applicable law or agreed to in writing, software
 *  distributed under the License is distributed on an "AS IS" BASIS,
 *  WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *  See the License for the specific language governing permissions and
 *  limitations under the License.
 */

/**
 * Package for classes that compose the model processed by the templating engine.<p>
 * 
 * Packages in the model package typically do not implement a lot of logic, but mainly provide the interface to the templating
 * engine.<p>
 * 
 * Therefore, they typically provide String data with necessary information about their respective language elements,
 * and access to their sub-structures (e.g. a type to its methods, a method to its parameters, etc.)<p>
 * 
 * To retrieve the information, they wrap an object from the {@link com.github.theinemann.jnitor.wrappers} package. 
 * 
 * @author Thomas Heinemann
 */
package com.github.theinemann.jnitor.model;

