[#ftl]
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

	[#list methods as method]
	/**
	 * @brief Calls Java method "${method.name}" on the referenced object.
	 *
	 * See The method's Javadoc documentation for more details.[#list method.parameters as parameter][#if parameter?is_first]
	 *[/#if]
	 * @param ${parameter.name} Object of java type ${parameter.javaType}[/#list]
	 */
	 [#if method.static]static [/#if]${method.returnType} ${method.name}([#list method.parameters as parameter]
		 ${parameter.type} ${parameter.name}[#sep], [/#sep][/#list]);

	[/#list]
private:
	JNIEnv *m_env;

	jclass m_class;

	jobject m_ref;

	static char[] m_name;
};

#endif //${macro}
