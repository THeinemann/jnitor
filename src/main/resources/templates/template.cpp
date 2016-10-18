[#ftl]
#include "${headerFileName}"

[#list packages as package]
namespace ${package} {
[/#list]

// Constructor from other reference
${classname}::${classname}(JNIEnv *env, jobject ref)
: m_env(env)
, m_class(jclass(env->NewGlobalRef(env->FindClass(m_name))))
, m_ref(env->NewGlobalRef(ref))
{
	// TODO: Assertion of type
}

// Constructor from other reference
${classname}::${classname}(const ${classname}& other)
: m_env(other.m_env)
, m_class(jclass(other.m_env->NewGlobalRef(other.m_env->FindClass(m_name))))
, m_ref(other.m_env->NewGlobalRef(other.m_ref))
{
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
	static const char signature[] = "${constructor.signature}";
	jmethodID mid = m_env->GetMethodID(m_class, "<init>", signature);
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

	[#if method.static]
	[#-- For static methods, these values have to be retrieved (because we have no object here) --]
	jclass m_class = m_env->FindClass(m_name);
	jclass m_ref = m_class;

	[/#if]

	jmethodID mid = m_env->Get[#if method.static]Static[/#if]MethodID(m_class, name, signature);
	[#if !method.void]return ${method.returnType}[/#if](m_env->${method.jniFunction}(
		m_ref, mid[#list method.parameters as parameter][#if parameter?is_first],
		[/#if]${parameter.name}[#sep], [/#sep][/#list]));
}

[/#list]
const char* const ${classname}::m_name = "${jniQualifiedName}";

[#list packages?reverse as package]
} // namespace ${package}
[/#list]
