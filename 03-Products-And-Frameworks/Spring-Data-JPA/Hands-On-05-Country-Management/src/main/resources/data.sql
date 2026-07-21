INSERT INTO country (country_code, country_name)
SELECT 'IN', 'India' FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM country WHERE country_code = 'IN');

INSERT INTO country (country_code, country_name)
SELECT 'US', 'United States' FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM country WHERE country_code = 'US');

INSERT INTO country (country_code, country_name)
SELECT 'GB', 'United Kingdom' FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM country WHERE country_code = 'GB');

INSERT INTO country (country_code, country_name)
SELECT 'CA', 'Canada' FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM country WHERE country_code = 'CA');

INSERT INTO country (country_code, country_name)
SELECT 'AU', 'Australia' FROM DUAL
WHERE NOT EXISTS (SELECT 1 FROM country WHERE country_code = 'AU');
