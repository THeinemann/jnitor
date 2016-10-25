[#ftl]
[#--
 Copyright 2016 Thomas Heinemann

 Licensed under the Apache License, Version 2.0 (the "License");
 you may not use this file except in compliance with the License.
 You may obtain a copy of the License at

     http://www.apache.org/licenses/LICENSE-2.0

 Unless required by applicable law or agreed to in writing, software
 distributed under the License is distributed on an "AS IS" BASIS,
 WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 See the License for the specific language governing permissions and
 limitations under the License.
--]
/* Warning: This file is generated.
 * Do not change it unless you know exactly what you are doing.
 * All your changes will be overwritten when this file is re-generated.
 */

#ifndef ${macro}
#define ${macro}

#include <jni.h>

[#list packages as package]
namespace ${package} {
[/#list]

class ${classname}
{
public:
	/**
	 * @brief Constructor from other reference
	 */
	${classname}(JNIEnv *env, jobject ref);

	/**
	 * @brief Copy constructor
	 *
	 * @note
	 * This constructor only creates a new reference. It does not copy
	 * the referenced object!
	 */
	${classname}(const ${classname}& other);

	[#-- Constructors to create an object are only included for non-abstract classes.--]
	[#if !abstract]
	[#list constructors as constructor]
	/**
	 * @brief Creates a new Java object by calling the respective Java constructor.
	 *
	 * @param env The JNI environment to create the object in.[#list constructor.parameters as parameter]
	 * @param ${parameter.name} Object of java type ${parameter.javaType}[/#list]
	 */
	${classname}(
        JNIEnv *env[#list constructor.parameters as parameter][#if parameter?is_first],
		[/#if]${parameter.type} ${parameter.name}[#sep], [/#sep][/#list]);

	[/#list]
	[/#if]
	/**
	 * @brief Destructor
	 */
	~${classname}();

	[#list methods as method]
	/**
	 * @brief Calls Java method "${qualifiedName}.${method.name}()".
	 *
	 * See The method's Javadoc documentation for more details.[#list method.parameters as parameter][#if parameter?is_first]
	 *[/#if]
	 * @param ${parameter.name} Object of java type ${parameter.javaType}[/#list]
	 */
	[#if method.static]static [/#if]${method.returnType} ${method.name}([#if method.static]JNIEnv *env, [/#if][#list method.parameters as parameter]
		${parameter.type} ${parameter.name}[#sep], [/#sep][/#list]);

	[/#list]
private:
	JNIEnv *m_env;

	jclass m_class;

	jobject m_ref;

	static const char* const m_name;
	static const char* const m_classNotFoundMessage;
};

[#list packages?reverse as package]
} // namespace ${package}
[/#list]


#endif //${macro}
