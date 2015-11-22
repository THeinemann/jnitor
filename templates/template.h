#ifndef ${macro}
#define ${macro}

#include <jni.h>
// TODO: Namespace

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

	// TODO: Constructor declarations

	/**
	 * @brief Destructor
	 */
	~${classname}();

	<#list methods as method>
	// TODO: Method declaration for <#if method.static>static method </#if>${method.name}
	</#list>
private:
	JNIEnv *m_env;

	jclass m_class;

	jobject m_ref;

	static char[] m_name;
};

#endif //${macro}
