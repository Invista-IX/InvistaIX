CREATE OR REPLACE FUNCTION antidupe_incc()
	RETURNS trigger AS $incc_trigger$
begin

	if(select exists 
	(select idincc as idincc, EXTRACT(MONTH from data) as mes, EXTRACT(YEAR from data) as ano 
			from incc 
			where EXTRACT(MONTH from data) = EXTRACT(MONTH from new.data)
			and EXTRACT(YEAR from data) = EXTRACT(YEAR from new.data))
	is true) then 
		return null;
	end if;
	return new;
	
end;
$incc_trigger$ language 'plpgsql';

CREATE OR REPLACE TRIGGER antidupe_incc_trigger
	BEFORE INSERT
	ON incc
	FOR EACH ROW
	EXECUTE PROCEDURE antidupe_incc();