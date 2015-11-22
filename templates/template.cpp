[#ftl]
#include "${headerFileName}"

// Constructor from other reference
${classname}::${classname}(JNIEnv *env, jobject ref)
: m_env(env)
, m_class(NewGlobalRef(findClass(env, m_name)))
, m_ref(NewGlobalRef(env, ref))
{
	// TODO: Assertion of type
}

// Constructor from other reference
${classname}::${classname}(const ${classname}& other)
: m_env(other.env)
, m_class(NewGlobalRef(findClass(env, m_name)))
, m_ref(NewGlobalRef(other.env, other.ref))
{
	// TODO: Assertion of type
}

// TODO: Constructor definitions

// Destructor
${classname}::~${classname}()
{
	DeleteGlobalRef(env, m_class);
	DeleteGlobalRef(env, m_ref);
}

char ${classname}::m_name = "${qualifiedName}";

[#list methods as method]
${method.returnType} ${classname}::${method.name}([#list method.parameters as parameter]
	 ${parameter.type} ${parameter.name}[#sep], [/#sep][/#list]);
{
	static const char[] name = "${method.name}";
	static const char[] signature = "${method.signature}";
	static const jmethodID mid = GetMethodId(m_env, m_class, signature);

	[#if !method.void]return [/#if]${method.jniFunction}(
		m_env, m_ref, mid[#list method.parameters as parameter][#if parameter?is_first],
		[/#if]${parameter.name}[#sep], [/#sep][/#list]);
}

[/#list]
