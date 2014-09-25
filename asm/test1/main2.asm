.data
hello:
    .string "Hello World\n"

.text
.global _WinMain@16
_WinMain@16:
    push $hello
    call _puts
    add $4, %esp

    ret
