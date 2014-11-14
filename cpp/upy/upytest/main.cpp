#define _USE_MATH_DEFINES
#include <math.h>
#include <iostream>
#include <stdint.h>
#include <stdio.h>
#include <string.h>
#include <sys/types.h>
//#define M_PI 3.14159265359
//#define M_E 2.71828
using namespace std;
#include "mpconfigport.h"//defines mp_uint //dont
#include "mpconfig.h"
//gives byte
#include "misc.h"

//gives mp_parse
#include "parse.h"
//requires byte
#include "qstr.h"
//gives mp_obj
//requires qstr
#include "obj.h"
#include "lexer.h"
#include "lexerunix.h"
//requires mp_obj
//gives mp_parse_show_exception
#include "parsehelper.h"

//gives MP_EMIT_OPT_NONE
#include "compile.h"

//gives nlr_buf_t
#include "nlr.h"
#include "runtime.h"

//gives mp_call_function_0

#include "runtime0.h"
#include "repl.h"
#include "gc.h"
#include "genhdr/py-version.h"
#include "input.h"
#include "stackctrl.h"

/*#include "genhdr/py-version.h"
//#include "genhdr/qstrdefs.generated.h"*/
static void do_str(const char *src) {
    mp_lexer_t *lex = mp_lexer_new_from_str_len(MP_QSTR__lt_stdin_gt_, src, strlen(src), 0);
    if (lex == NULL) {
        return;
    }

    mp_parse_error_kind_t parse_error_kind;
    mp_parse_node_t pn = mp_parse(lex, MP_PARSE_SINGLE_INPUT, &parse_error_kind);

    if (pn == MP_PARSE_NODE_NULL) {
        // parse error
        mp_parse_show_exception(lex, parse_error_kind);
        mp_lexer_free(lex);
        return;
    }

    // parse okay
    qstr source_name = mp_lexer_source_name(lex);
    mp_lexer_free(lex);
    mp_obj_t module_fun = mp_compile(pn, source_name, MP_EMIT_OPT_NONE, true);

    if (mp_obj_is_exception_instance(module_fun)) {
        // compile error
        mp_obj_print_exception(module_fun);
        return;
    }

    nlr_buf_t nlr;
    if (nlr_push(&nlr) == 0) {
        mp_call_function_0(module_fun);
        nlr_pop();
    } else {
        // uncaught exception
        mp_obj_print_exception((mp_obj_t)nlr.ret_val);
    }
}

int main()
{
	cout << "Hello World!" << endl;
	return 0;
}

