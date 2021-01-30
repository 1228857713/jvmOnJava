/*
 * Copyright (c) 2005, 2006, Oracle and/or its affiliates. All rights reserved.
 * ORACLE PROPRIETARY/CONFIDENTIAL. Use is subject to license terms.
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 *
 */

#ifndef _DEFINES_H
#define _DEFINES_H

#include "java.h"

/*
 * This file contains commonly defined constants used only by main.c
 * and should not be included by another file.
 */
#ifndef FULL_VERSION
/* make sure the compilation fails */
#error "FULL_VERSION must be defined"
#endif

#if defined(JDK_MAJOR_VERSION) && defined(JDK_MINOR_VERSION)
#define DOT_VERSION JDK_MAJOR_VERSION "." JDK_MINOR_VERSION
#else
/* make sure the compilation fails */
#error "JDK_MAJOR_VERSION and JDK_MINOR_VERSION must be defined"
#endif


#ifdef JAVA_ARGS
static const char* const_progname = "java";
static const char* const_jargs[] = JAVA_ARGS;
/*
 * ApplicationHome is prepended to each of these entries; the resulting
 * strings are concatenated (separated by PATH_SEPARATOR) and used as the
 * value of -cp option to the launcher.
 */
#ifndef APP_CLASSPATH
#define APP_CLASSPATH        { "/lib/tools.jar", "/classes" }
#endif /* APP_CLASSPATH */
static const char* const_appclasspath[] = APP_CLASSPATH;
#else  /* !JAVA_ARGS */
#ifdef PROGNAME
static const char* const_progname = PROGNAME;
#else
static char* const_progname = NULL;
#endif
static const char** const_jargs = NULL;
static const char** const_appclasspath = NULL;
#endif /* JAVA_ARGS */

#ifdef LAUNCHER_NAME
static const char* const_launcher = LAUNCHER_NAME;
#else  /* LAUNCHER_NAME */
static char* const_launcher = NULL;
#endif /* LAUNCHER_NAME */

#ifdef EXPAND_CLASSPATH_WILDCARDS
static const jboolean const_cpwildcard = JNI_TRUE;
#else
static const jboolean const_cpwildcard = JNI_FALSE;
#endif /* EXPAND_CLASSPATH_WILDCARDS */

#if defined(NEVER_ACT_AS_SERVER_CLASS_MACHINE)
static const jint const_ergo_class = NEVER_SERVER_CLASS;
#elif defined(ALWAYS_ACT_AS_SERVER_CLASS_MACHINE)
static const jint const_ergo_class = ALWAYS_SERVER_CLASS;
#else
static const jint const_ergo_class = DEFAULT_POLICY;
#endif /* NEVER_ACT_AS_SERVER_CLASS_MACHINE */

#endif /*_DEFINES_H */
