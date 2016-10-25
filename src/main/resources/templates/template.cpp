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

#include "${headerFileName}"
#include <stdexcept>

[#list packages as package]
namespace ${package} {
[/#list]

// Constructor from other reference
${classname}::${classname}(JNIEnv *env, jobject ref)
: m_env(env)
, m_class(jclass(env->NewGlobalRef(env->FindClass(m_name))))
, m_ref(env->NewGlobalRef(ref))
{
	if (m_class == NULL) {
		throw std::runtime_error(m_classNotFoundMessage);
	}
	// TODO: Assertion of type
}

// Constructor from other reference
${classname}::${classname}(const ${classname}& other)
: m_env(other.m_env)
, m_class(jclass(other.m_env->NewGlobalRef(other.m_env->FindClass(m_name))))
, m_ref(other.m_env->NewGlobalRef(other.m_ref))
{
	if (m_class == NULL) {
		throw std::runtime_error(m_classNotFoundMessage);
	}
	// TODO: Assertion of type
}

[#-- Constructors to create an object are only included for non-abstract classes.--]
[#if !abstract]
[#list constructors as constructor]
// Constructor that creates a new Java object
${classname}::${classname}(
	JNIEnv *env[#list constructor.parameters as parameter][#if parameter?is_first],
	[/#if]${parameter.type} ${parameter.name}[#sep], [/#sep][/#list])
: m_env(env)
, m_class(jclass(env->NewGlobalRef(env->FindClass(m_name))))
{
	if (m_class == NULL) {
		throw std::runtime_error(m_classNotFoundMessage);
	}

	static const char signature[] = "${constructor.signature}";
	static const char methodNotFoundMessage[] = "Constructor for class ${qualifiedName} with signature ${constructor.signature} was not found on class.";

	jmethodID mid = m_env->GetMethodID(m_class, "<init>", signature);

	if (mid == NULL) {
		throw std::runtime_error(methodNotFoundMessage);
	}

	m_ref = m_env->NewGlobalRef(m_env->NewObject(
				m_class, mid[#list constructor.parameters as parameter][#if parameter?is_first],
				[/#if]${parameter.name}[#sep], [/#sep][/#list]));
}

[/#list]
[/#if]
// Destructor
${classname}::~${classname}()
{
	m_env->DeleteGlobalRef(m_class);
	m_env->DeleteGlobalRef(m_ref);
}

[#list methods as method]
${method.returnType} ${classname}::${method.name}([#if method.static]JNIEnv *m_env, [/#if][#list method.parameters as parameter]
	 ${parameter.type} ${parameter.name}[#sep], [/#sep][/#list])
{
	static const char name[] = "${method.name}";
	static const char signature[] = "${method.signature}";
	static const char methodNotFoundMessage[] = "Method ${qualifiedName}.${method.name}() with signature ${method.signature} was not found on class.";

	[#if method.static]
	[#-- For static methods, these values have to be retrieved (because we have no object here) --]
	jclass m_class = m_env->FindClass(m_name);
	jclass m_ref = m_class;

	if (m_class == NULL) {
		throw std::runtime_error(m_classNotFoundMessage);
	}

	[/#if]

	jmethodID mid = m_env->Get[#if method.static]Static[/#if]MethodID(m_class, name, signature);

	if (mid == NULL) {
		throw new std::runtime_error(methodNotFoundMessage);
	}

	[#if !method.void]return ${method.returnType}[/#if](m_env->${method.jniFunction}(
		m_ref, mid[#list method.parameters as parameter][#if parameter?is_first],
		[/#if]${parameter.name}[#sep], [/#sep][/#list]));
}

[/#list]
const char* const ${classname}::m_name = "${jniQualifiedName}";
const char* const ${classname}::m_classNotFoundMessage = "Class ${qualifiedName} not found in classpath.";

[#list packages?reverse as package]
} // namespace ${package}
[/#list]
