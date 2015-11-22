#include "${headerFileName}"

// Constructor from other reference
${classname}::${classname}(JNIEnv *env, jobject ref)
: m_env(env)
, m_class(findClass(env, m_name))
, m_ref(NewGlobalRef(env, ref))
{
	// TODO: Assertion of type
}

// Constructor from other reference
${classname}::${classname}(const ${classname}& other)
: m_env(other.env)
, m_class(env, m_name)
, m_ref(NewGlobalRef(other.env, other.ref))
{
	// TODO: Assertion of type
}

// TODO: Constructor definitions

// Destructor
${classname}::~${classname}()
{
	DeleteGlobalRef(env, m_ref);
}

char ${classname}::m_name = "${qualifiedName}";

<#list methods as method>
// TODO: Method definition for <#if method.static>static method </#if>${method.name}
</#list>
