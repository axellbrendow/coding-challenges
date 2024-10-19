DROP TABLE IF EXISTS bills;
CREATE TABLE bills 
(
    id INT,
    title VARCHAR(512),
    sponsor_id INT
);
INSERT INTO bills (id, title, sponsor_id) VALUES ('2952375', 'H.R. 5376: Build Back Better Act', '412211');
INSERT INTO bills (id, title, sponsor_id) VALUES ('2900994', 'H.R. 3684: Infrastructure Investment and Jobs Act', '400100');


DROP TABLE IF EXISTS legislators;
CREATE TABLE legislators 
(
    id INT,
    name VARCHAR(512)
);
INSERT INTO legislators (id, name) VALUES ('904789', 'Rep. Don Bacon (R-NE-2)');
INSERT INTO legislators (id, name) VALUES ('1603850', 'Rep. Jamaal Bowman (D-NY-16)');
INSERT INTO legislators (id, name) VALUES ('1852382', 'Rep. Cori Bush (D-MO-1)');
INSERT INTO legislators (id, name) VALUES ('904796', 'Rep. Brian Fitzpatrick (R-PA-1)');
INSERT INTO legislators (id, name) VALUES ('15318', 'Rep. Andrew Garbarino (R-NY-2)');
INSERT INTO legislators (id, name) VALUES ('1269775', 'Rep. Anthony Gonzalez (R-OH-16)');
INSERT INTO legislators (id, name) VALUES ('412649', 'Rep. John Katko (R-NY-24)');
INSERT INTO legislators (id, name) VALUES ('412421', 'Rep. Adam Kinzinger (R-IL-16)');
INSERT INTO legislators (id, name) VALUES ('15367', 'Rep. Nicole Malliotakis (R-NY-11)');
INSERT INTO legislators (id, name) VALUES ('412487', 'Rep. David McKinley (R-WV-1)');
INSERT INTO legislators (id, name) VALUES ('1269767', 'Rep. Alexandria Ocasio-Cortez (D-NY-14)');
INSERT INTO legislators (id, name) VALUES ('905216', 'Rep. Ilhan Omar (D-MN-5)');
INSERT INTO legislators (id, name) VALUES ('1269778', 'Rep. Ayanna Pressley (D-MA-7)');
INSERT INTO legislators (id, name) VALUES ('412393', 'Rep. Tom Reed (R-NY-23)');
INSERT INTO legislators (id, name) VALUES ('400380', 'Rep. Chris Smith (R-NJ-4)');
INSERT INTO legislators (id, name) VALUES ('1269790', 'Rep. Rashida Tlaib (D-MI-13)');
INSERT INTO legislators (id, name) VALUES ('400414', 'Rep. Fred Upton (R-MI-6)');
INSERT INTO legislators (id, name) VALUES ('17941', 'Rep. Jeff Van Drew (R-NJ-2)');
INSERT INTO legislators (id, name) VALUES ('400440', 'Rep. Don Young (R-AK-1)');
INSERT INTO legislators (id, name) VALUES ('412211', 'Rep. John Yarmuth (D-KY-3)');


DROP TABLE IF EXISTS vote_results;
CREATE TABLE vote_results 
(
    id INT,
    legislator_id INT,
    vote_id INT,
    vote_type INT
);
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92516784', '400440', '3321166', '2');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92516770', '17941', '3321166', '2');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92516768', '400414', '3321166', '2');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92516753', '400380', '3321166', '2');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92516734', '412393', '3321166', '2');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92516711', '412487', '3321166', '2');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92516702', '15367', '3321166', '2');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92516688', '412421', '3321166', '2');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92516683', '412649', '3321166', '2');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92516647', '1269775', '3321166', '2');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92516640', '15318', '3321166', '2');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92516632', '904796', '3321166', '2');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92516579', '904789', '3321166', '2');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92516553', '1269790', '3321166', '1');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92516513', '1269778', '3321166', '1');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92516500', '905216', '3321166', '1');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92516499', '1269767', '3321166', '1');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92516373', '1852382', '3321166', '1');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92516368', '1603850', '3321166', '1');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92280168', '1269790', '3314452', '2');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92280136', '1269778', '3314452', '2');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92280128', '905216', '3314452', '2');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92280127', '1269767', '3314452', '2');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92280007', '1852382', '3314452', '2');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92279998', '1603850', '3314452', '2');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92279979', '400440', '3314452', '1');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92279965', '17941', '3314452', '1');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92279964', '400414', '3314452', '1');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92279943', '400380', '3314452', '1');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92279920', '412393', '3314452', '1');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92279888', '412487', '3314452', '1');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92279879', '15367', '3314452', '1');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92279858', '412421', '3314452', '1');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92279850', '412649', '3314452', '1');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92279828', '1269775', '3314452', '1');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92279823', '15318', '3314452', '1');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92279817', '904796', '3314452', '1');
INSERT INTO vote_results (id, legislator_id, vote_id, vote_type) VALUES ('92279758', '904789', '3314452', '1');


DROP TABLE IF EXISTS votes;
CREATE TABLE votes 
(
    id INT,
    bill_id INT
);
INSERT INTO votes (id, bill_id) VALUES ('3314452', '2900994');
INSERT INTO votes (id, bill_id) VALUES ('3321166', '2952375');


-- legislators-support-oppose-count.csv

select
    l.id as legislator_id,
    l.name,
    COUNT(case when vr.vote_type = 1 then 1 end) as num_supported_bills,
    COUNT(case when vr.vote_type = 2 then 1 end) as num_opposed_bills
from legislators as l
LEFT JOIN vote_results as vr on l.id = vr.legislator_id
group by l.id, l.name;


-- bills-support-oppose-count.csv

select
    b.id,
    b.title,
    COUNT(case when vr.vote_type = 1 then 1 end) as supporter_count,
    COUNT(case when vr.vote_type = 2 then 1 end) as opposer_count,
    coalesce(l.name, 'Unknown') as primary_sponsor
from bills as b
LEFT JOIN legislators as l on b.sponsor_id = l.id
LEFT JOIN votes as v on b.id = v.bill_id
left join vote_results as vr on v.id = vr.vote_id
group by b.id, b.title, l.name;
