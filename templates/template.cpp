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

[#-- Constructors to create an object are only included for non-abstract classes.--]
[#if !abstract]
[#list constructors as constructor]
// Constructor that creates a new Java object
${classname}::${classname}(
	JNIEnv *env,[#list constructor.parameters as parameter]
	${parameter.type} ${parameter.name}[#sep], [/#sep][/#list])
: m_env(env)
, m_class(NewGlobalRef(findClass(env, m_name)))
{
	static const char[] signature = "${constructor.signature}";
	jmethodID mid = GetMethodID(m_env, m_class, "<init>", signature);
	m_ref = NewGlobalRef(NewObject(
				env, cls, mid,
				[#list constructor.parameters as parameter]${parameter.name}[#sep], [/#sep][/#list]));
}

[/#list]
[/#if]
// Destructor
${classname}::~${classname}()
{
	DeleteGlobalRef(env, m_class);
	DeleteGlobalRef(env, m_ref);
}

[#list methods as method]
${method.returnType} ${classname}::${method.name}([#list method.parameters as parameter]
	 ${parameter.type} ${parameter.name}[#sep], [/#sep][/#list]);
{
	static const char[] name = "${method.name}";
	static const char[] signature = "${method.signature}";
	jmethodID mid = GetMethodID(m_env, m_class, name, signature);

	[#if !method.void]return [/#if]${method.jniFunction}(
		m_env, m_ref, mid[#list method.parameters as parameter][#if parameter?is_first],
		[/#if]${parameter.name}[#sep], [/#sep][/#list]);
}

[/#list]
char ${classname}::m_name = "${jniQualifiedName}";
