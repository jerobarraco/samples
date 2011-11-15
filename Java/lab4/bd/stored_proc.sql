create or alter procedure factorial(numero int) 
	returns (fact int)
as
begin
	if (numero<1) then 
		fact = 1;
	else
		execute procedure 
			factorial 
			numero-1 
		returning_values
			numero;
		-- q sintaxis mas fea
		
end;
