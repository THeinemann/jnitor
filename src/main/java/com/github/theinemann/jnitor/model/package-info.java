
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

