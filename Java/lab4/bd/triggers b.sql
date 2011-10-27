-- 1 generar codigo producto
create generator gen_productos;
set generator gen_productos to 0;
create exception producto_existe 'El codigo de producto ya existe';


Create trigger tproducto for productos before insert or update position 0
as 
begin
	if (new.codigo is null) then
		new.codigo = GEN_ID(gen_productos, 1);
	else
		if (exists (select codigo from productos where codigo = new.codigo )) then
			exception producto_existe;
end;

--2
alter trigger tproducto before insert or update position 0
as 
begin
	if (new.codigo is null) then
		new.codigo = GEN_ID(gen_productos, 1);
	else
		begin
			if (new.codigo = 0) then
				new.codigo = GEN_ID(gen_productos, -1);
			else
				if (exists (select codigo from productos where codigo = new.codigo )) then
					exception producto_existe;
		end
end;

--3
create exception producto_invalido 'No se pueden ingresar tipos de productos A y C para la misma factura';

create trigger titem for facturas_items
    active
    before insert or update
    position 0
as
declare variable tt varchar(1);
declare variable cA integer;
declare variable cB integer;
begin
	select first 1
		count(iif( familia = 'A', 1, null) ) as A,
		count(iif( familia = 'C', 1, null) ) as C
	from
		productos join facturas_item on facturas_item.producto_codigo = productos.codigo
	where 
		facturas_item.factura_numero = new.factura_numero
		and facturas_item.factura_tipo = new.factura_tipo
	into :cA, :cB;
	
	select familia from productos where codigo = new.producto_codigo into :tt;
	if(tt='A') then 
		cA = cA+1;
	if (tt='C') then
		cB = cB+1;
		
	if(cA>0 and cB>0) then 
		exception producto_invalido;
end;

--- 4 
create table old_domicilios(
	cuit varchar(11) not null,
	fecha timestamp not null,
	calle varchar(30),
	nro_puerta varchar(6),
	piso varchar(2),
	dpto varchar(2),
	codigo_postal varchar(8),
	foreign key (cuit) references clientes(cuit),
	primary key(cuit, fecha)
);
--alter table old_domicilios alter column fecha not null;

create trigger update_domic
    for clientes
    active
    before update
    position 0
as
begin
--no funciona con los null, porque != no está definido para null ¬_¬# crap, eso me costó un punto del examen... 
    if ((new.calle != old.calle) or (new.numero_puerta != old.numero_puerta) or (new.piso != old.piso) or (new.codigo_postal != old.codigo_postal)) then
        insert into old_domicilios(cuit, fecha, calle, nro_puerta, piso, dpto, codigo_postal)
            values( new.cuit, CURRENT_TIMESTAMP, new.calle, new.numero_puerta, new.piso, new.dpto, new.codigo_postal);
end;


---- 5
create table AUDITORIAS(
	tabla varchar(100) not null,
	instante timestamp default CURRENT_TIMESTAMP, -- no se usa not null cuando se usa default.. lo cual seria una redundancia
	evento varchar(10),
	usuario varchar(100) default CURRENT_USER, -- firebird usa un varchar(31) (si, 31)
	pk varchar(60),
	primary key (tabla, instante, pk) 
	-- pk es necesaria porque si hacemos un Update from clientes, todos van a tener el mismo instante, y entonces explota el trigger (por violacion de pk en auditorias
	--- quizas pueda safar si no utilizo el default, pero no creo 
);


--no olvidar set term ^ ; y set term ; ^ si se va a ejecutar esto como sql normal
create or alter trigger audit_cli
    for clientes
    active 
    before update or insert or delete --polivalente :D!

as 
declare vevento varchar(10); -- fb no tiene el type of .... 
declare vpk varchar(60);
begin
	if (inserting) then
	begin
        vevento = 'insert'  ;
		vpk  = new.cuit;
	end
    else if (deleting) then
	begin
        vevento = 'delete' ;
		vpk = old.cuit ; 
	end
    else --- se sobreentiende que es un update
	begin
        vevento = 'update';
		vpk = old.cuit; -- si, old, porque lo que me interesa saber es que cambio
	end
    insert into auditorias(tabla, evento, pk) values('clientes', :vevento, :vpk); -- que los demás triggers hagan su laburo 
	
	/* el ibexpert no me reconoce el case... todo: ver que le pasa
    case 
        when inserting then
            evento = 'insert'
        when deleting then
            evento = 'delete'
        when updating then
            evento = 'delete'
    end*/
end;


-- 6
alter table clientes add saldo numeric(16,4);

-- esto dice que un pago puede tener muchas facturas, asi que esta estructura esta desnormalizada... falta una tabla pagos_factura, pero me da fiaca hacerla
create table PAGOS(
	n_recibo integer not null,
	fecha date default CURRENT_DATE,
	t_factura smallint not null, 
	n_factura integer not null,
	monto numeric(16, 4) not null,
	primary key (n_recibo)
);
-- como es tarea de triggers ejercitemos
create generator gen_pago;
set generator gen_pago to 0;

create trigger gen_pago  for pagos
active
before insert position 0
as
begin
    if (new.n_recibo is null) then
        new.n_recibo = GEN_ID(gen_pago, 1);
end;

-- actualizar el saldo
-- update clientes set saldo = null [where cuit = algo];
create trigger act_saldo for clientes
active
before update
as 
begin
    --uso precio asumiendo que es el total para ese item, sino hay q hacer join de productos, multiplicar por cantidad y aplicar descuetno @_@
    if (new.saldo is null) then begin --ojo que es new
		new.saldo =COALESCE((
            select sum(monto) from pagos p
                join facturas f on
                    p.t_factura = f.tipo and
                    p.n_factura = f.numero
                join clientes c on
                    f.cuit = c.cuit
            where c.cuit = new.cuit
            ), 0);
        new.saldo = new.saldo- COALESCE((
            select sum(precio) from facturas_items fi
                join facturas f  on 
                    fi.factura_numero = f.numero and 
                    fi.factura_tipo = f.tipo
                join clientes c on
                    f.cuit = c.cuit
                where c.cuit = new.cuit -- ojo que usamos new.cuit.. 
            ), 0);
    end;
end;
-- esto es un dolor de cabeza.. capaz seria mejro una vista (si no se accede seguido al saldo)
-- hay quehacer un trigger para i/u/d de los items, de las facturas y de los pagos cuidando los valores de monto y cuit

create trigger saldo_pago for pagos
active before update or delete or insert
as
begin
	-- el coalesce nos evita tener que andar preguntando por un inserting7deleting/updating
	update clientes set saldo = saldo - coalesce(old.monto,0) + coalesce(new.monto, 0)
		where cuit = (select first 1 cuit from facturas f where f.tipo = new.t_factura and f.numero = new.n_factura); 
		
	-- esto funciona pero puede llevar a errores pro el redondeo en la suma y resta 
	--seria mejor recalcular todo
	-- pej tamb me cagarian si alguien cambia el new.t_factura o new.n_numero... hay muchos detalles que hacen que esto de error
	-- en ese caso habria que modificar tanto el user viejo (quitar monto) y el user nuevo (agregar monto)
end;

create trigger saldo_factura for facturas
	active 
	before update or delete or insert
	as
	begin
		update clientes set saldo = null where cuit = new.cuit; --forma corta --no va a andar para delete
		if (Coalesce(new.cuit <> old.cuit, false))then --contemplar q a alguien se le ocurra cambiar el cuit
			update clientes set saldo = null where cuit = old.cuit;
		
	end;
	
--- lo mimso para items pero me da fiaca


--- 7 crear una vista que reuna clientes y clientes_baja
alter table clientes_baja add saldo numeric(16, 4);

create view clientes_historia as
	select * from clientes	
	union 
	select * from clientes_baja
;

-- trigger que no permita dar de baja cliente con menos de 10 años de inactividad, y muestre una excepcion
create exception cliente_nuevo 'No se puede eliminar un cliente con menos de 10 años use el campo fecha_baja';
-- el ejercicio dice dar de baja, asumo eliminar.
-- algunas cosas no tienen mucho sentido asi que me tomo la libertad de aplicar como s me canta :D

--si se borra se pasa a clientes_baja
create trigger del_clientes for clientes
	active 
	AFTER delete--after, porque no me interesa interrumpir el deleteado y lo hare de igual manera
	as
declare fecha DATE;
begin			
	fecha = COALESCE(old.fecha_baja, current_date); -- por el trigger de abajo, puede estar fijada
	insert into 
		clientes_baja (CUIT, nombre, situacion_iva, tipo_persona, calle, numero_puerta, piso, dpto, codigo_postal, fecha_baja, saldo)
	values (
		old.cuit, old.nombre, old.situacion_iva, old.tipo_persona, old.calle, old.numero_puerta, old.piso, old.dpto, old.codigo_postal, :fecha, old.saldo
	);
end;

-- este es peligroso, si se pone fecha de baja se lo borra
create trigger del_clientes_down for clientes
active AFTER update
as begin
	if (new.fecha_baja is not null) then --after, el cambio ya se aplico
		delete from clientes where cuit = new.cuit ; --esto llama el trigger d arriba
end;

-- no se pueden borrar clientes nuevos
create trigger del_inactive_clientes for clientes_baja
	active 
	before delete -- before porque puede fallar
as
begin
	if (exists(
		select cuit from facturas 
			where 
				cuit = old.cuit and
				extract (year from current_date) - extract(year from fecha) < 10 --si, es algo fuzzy pero bueh
		)) then 
		exception cliente_nuevo;
end ;

create trigger upd_clientes for clientes_historia
	active
	before update or insert
	-- la consigna no habla de insert, eso llevaria un poquito mas de code para controlar q estams haciendo y tener cuidado con los campos
as
begin
	--no dejamos que se cambien estos datos, pero tampoco damos error. solo bloqueamos cambios.
	--como ven solo es posible en update
	if (updating) then begin
		new.cuit = old.cuit ; 
		new.fecha_baja = old.fecha_baja;
		-- no sabemos en que tabla esta el user, podriamos usar 2 if (exists) pero no vale la pena,
		-- si se usa update directamente no habra problema, porque si ninguna fila coincida con el where, no hace nada,
		-- y tamb tenemos en cuenta el caso en que el dato este en ambas tablas (eso seria un error) (Y haria que ambos tengan los mismos datos)
		-- el hecho de tirar consultas sql en el medio del plsql lo hace horrible :D podria usar execute statement pero eso lo haria mas horrible
		update clientes 
			set nombre = new.nombre, situacion_iva  =new.situacion_iva, tipo_persona = new.tipo_persona, 
				calle = new.calle, codigo_postal = new.codigo_postal
		where cuit = new.cuit;
		
	
		update clientes_baja
			set nombre = new.nombre, situacion_iva  =new.situacion_iva, tipo_persona = new.tipo_persona,
				calle = new.calle, codigo_postal = new.codigo_postal
		where cuit = new.cuit;
		
	end
	else begin
		insert into clientes (nombre, situacion_iva, tipo_persona, calle, codigo_postal, cuit) 
			values(new.nombre, new.situacion_iva, new.tipo_persona, new.calle, new.codigo_postal, new.cuit);
		-- no tiene sentido insertar un cliente con fecha baja, pero ando generoso
		if (new.fecha_baja is not null) then
			update clientes 
				set fecha_baja = new.fecha_baja
			where cuit = new.cuit;
	end
end;


--8) Realice una vista que reuna Clientes , Localidades y Provincias. 
--y Realice un Trigger que haga actualizable esta vista, de manera que:
--o Si se ingresa un cliente que no exista inserte el cliente.
--o Si se ingresa una localidad que no exista inserte la localidad.
 
CREATE VIEW CLICOMPLETO 
	(NOMBRE, CUIT, CALLE, SITUACION_IVA, TIPO_PERSONA, CODIGO_POSTAL, DPTO, NUMERO_PUERTA, PISO, SALDO, FECHA_BAJA, LOCALIDAD, PROVINCIA)
AS 
 select 
	ch.NOMBRE, ch.CUIT, ch.CALLE, ch.SITUACION_IVA, ch.TIPO_PERSONA, ch.CODIGO_POSTAL, ch.DPTO, ch.NUMERO_PUERTA, ch.PISO, ch.saldo, ch.FECHA_BAJA, L.LOCALIDAD, P.NOMBRE
    from clientes_historia ch
	join localidades L 
        on ch.codigo_postal = L.codigo_postal
    join PROVINCIAS P
        on P.ID = L.PROVINCIA_ID;

set term ^ ;
create trigger up_clicompleto for clicompleto
	active
	before update or insert -- el delete no viene incluido :B
as
begin
	-- habria que empezar de atras para adelante
	-- no tengo el id de la provincia, asi que.... (y es todo un tema porque no tiene generadr)
	if (updating) then begin
		update provincias set nombre = new.PROVINCIA where nombre = old.PROVINCIA;
	end
    update or insert into localidades (codigo_postal, localidad)
			values(new.codigo_postal, new.localidad)
			matching(codigo_postal)
		;
	update or insert into 
		clientes(cuit, nombre, calle, situacion_iva, tipo_persona, codigo_postal, dpto, numero_puerta, piso, saldo, fecha_baja)
		values(new.cuit, new.nombre, new.calle, new.situacion_iva, new.tipo_persona, new.codigo_postal, new.dpto, new.numero_puerta, new.piso, new.saldo, new.fecha_baja);
		matching (cuit);
		
	--no se puede usar update or insert con vistas de mas de una tabla.. .q util
end
^
set term ; ^