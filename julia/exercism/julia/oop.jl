#=
oop:
- Julia version: 
- Author: nande
- Date: 2021-08-23
=#

function bind(this, fun)
    return function bound_fun(x...)
        return fun(this, x...)
    end
end

mutable struct Response
    data::String
    headers::Dict

    addContent::Function
    setHeader::Function
    getHeaders::Function
    getContents::Function
    getResponse::Function

    function Response()
        this = new()

        this.data = ""

        this.headers = Dict{String, String}()
        this.addContent = bind(this, Response_addContent)
        this.setHeader = bind(this, Response_setHeader)
        return this
    end
end

function Response_addContent(self::Response, text)
    self.data *= text
end
function Response_setHeader(self::Response, header, value)
    self.headers[header] = value
end

r = Response()
r.addContent("test")
r.addContent("test2")
r.setHeader("accept", "*")

println(r.data)
println(r.headers)
