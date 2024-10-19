/*
You can test this script at https://paiza.io/en/projects/new?language=mysql

-----

Query 1, when OrderID = 1, returns:

Quantity	rownum	total
1	        1   	1
1	        2   	2
2	        3   	3
2	        4   	4

-----

Query 2, when OrderID = 1, returns:

AVG(Quantities.Quantity)
1.5000

-----

Query 3 returns:

OrderID	Median	@orderid = OrderID
1	    1.5000	NULL
2	    3.0000	NULL
*/

create table OrderDetails(OrderID integer, Quantity integer);

insert into OrderDetails(OrderID, Quantity) values(1, 1);
insert into OrderDetails(OrderID, Quantity) values(1, 1);
insert into OrderDetails(OrderID, Quantity) values(1, 2);
insert into OrderDetails(OrderID, Quantity) values(1, 2);

insert into OrderDetails(OrderID, Quantity) values(2, 2);
insert into OrderDetails(OrderID, Quantity) values(2, 2);
insert into OrderDetails(OrderID, Quantity) values(2, 4);
insert into OrderDetails(OrderID, Quantity) values(2, 4);

SELECT  -- Query 3
    E_out.OrderID AS OrderID,

    (
        SELECT AVG(Quantities.Quantity) FROM (  -- Query 2

            SELECT  -- Query 1
                E.Quantity as Quantity,
                IF(@orderid = OrderID, @rownum := @rownum + 1, @rownum := 0) as rownum,
                @total := @rownum as total
            FROM OrderDetails as E, (select @rownum := 0) as r
            WHERE E.OrderID = E_out.OrderID
            ORDER BY E.Quantity

        ) AS Quantities
        WHERE Quantities.rownum IN (CEIL(@total / 2), CEIL((@total + 1) / 2))
    ) as Median,

    @orderid = OrderID

FROM OrderDetails AS E_out
GROUP BY E_out.OrderID
