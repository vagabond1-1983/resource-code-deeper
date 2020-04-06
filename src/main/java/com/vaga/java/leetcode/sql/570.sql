## 找出有至少5个下属的经理
SELECT NAME 
FROM
	570Employee 
WHERE
	Id IN (
	SELECT
		a.ManageId 
	FROM
		570Employee a
		JOIN 570Employee b ON a.ManageId = b.Id 
	GROUP BY
		a.ManageId 
	HAVING
	COUNT( a.Id ) >= 5 
	)