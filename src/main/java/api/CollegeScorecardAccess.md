# Documentation for accessing the College Score Card API

This document explains:
* How to define and execute queries as URLs
* Refining query results using option parameters
* Extracting query results in JSON and CSV format
* Detecting query errors
* Potential fields to query

## API Access for the College Scorecard Data
Please note: A valid API key is required for those looking to access the API for the [College Scorecard Data](https://collegescorecard.ed.gov/data/documentation/), which is proxied through an API gateway. Developers may register a key at https://api.data.gov/signup for API access.

## Introduction to Queries

Each query is expressed as a URL, containing:

* The **URL Path** to the Open Data Maker service, e.g.
  `https://collegescorecard.ed.gov/` (for Open Data Maker running its own
  server), or  `https://api.data.gov/ed/collegescorecard/v1/` (for Open
  Data Maker proxied through an API gateway)
* The **API Version String**. Currently the only supported version string is: `v1`
* The **Endpoint** representing a particular dataset, e.g. `schools`. Endpoint
  names are usually plural.

* The **Query String** containing a set of named key-value pairs that
  represent the query, which incude
    * **Field Parameters**, specifying a value (or set of values) to match
      against a particular field, and


### Query Example

Here's an example query URL:

Note: YOU MUST ADD YOUR API KEY

```
https://api.data.gov/ed/collegescorecard/v1/schools.json?school.degrees_awarded.predominant=2,3&fields=id,school.name,2013.student.size
```

In this query URL:

* `https://api.data.gov/ed/collegescorecard/v1/` is the URL Path.
* `v1` is the API Version String, followed by `/`, which separates it from the Endpoint.
* `schools` is the Endpoint. Note the plural.
* `.json` is the Format. Note the dot between the Endpoint and Format. Also note that, since JSON is the default output format, we didn't _have_ to specify it.
* In keeping with standard [URI Query String syntax](https://en.wikipedia.org/wiki/Query_string), the `?` and `&` characters are used to begin and separate the list of query parameters.
* `school.degrees_awarded.predominant=2,3` is a Field Parameter. In this case, it's searching for records which have a `school.degrees_awarded.predominant` value of either `2` or `3`.
* `_fields=id,school.name,2013.student.size` is an Option Parameter, as denoted by the initial underscore character. `_fields` is used to limit the output fields to those in the given list. We strongly recommend using the `_fields` parameter to reduce the amount of data returned by the API, thus increasing performance.

### JSON Output Example

Here's an example of the JSON document that Open Data Maker would provide
in response to the above query:

```json
{
  "metadata": {
    "total": 3667,
    "page": 0,
    "per_page": 20
  },
  "results": [
    {
      "id": 190752,
      "school.name": "Yeshiva of Far Rockaway Derech Ayson Rabbinical Seminary",
      "2013.student.size": 57
    },
    {
      "id": 407009,
      "school.name": "Arizona State University-West",
      "2013.student.size": 3243
    },
    {
      "id": 420574,
      "school.name":"Arizona State University-Polytechnic",
      "2013.student.size": 3305
    }

    // ... (further results removed) ...
  ]
}
```

A successful query will return a JSON with two top-level elements:

* **`metadata`**: A JSON Object containing information about the results returned. The metadata fields are:
    * `total`: The total number of records matching the query
    * `page`: The page number for this result set
    * `per_page`: The number of records returned in a single result set. (For more information about the `page` and `per_page` fields, see the section on [Pagination](#pagination-with-_page-and-_per_page))
* **`results`**: A JSON Array of record objects. Due to the use of the `_fields` option in this query, there are only three fields in each record - the three fields specified in the `_fields` parameter. When the `_fields` parameter is omitted, the full record is provided.

### Error Example

Let's change the query so as to generate an error when it's executed:

```
https://api.data.gov/ed/collegescorecard/v1/schools.json?school.degrees_awarded.predominant=frog&_fields=id,school.name,wombat
```

This is the JSON document returned:

```json
{
  "errors": [
    {
      "error": "field_not_found",
      "input": "wombat",
      "message": "The input field 'wombat' (in the fields parameter) is not a field in this dataset."
    },
    {
      "error": "parameter_type_error",
      "parameter": "school.degrees_awarded.predominant",
      "input": "frog",
      "expected_type": "integer",
      "input_type": "string",
      "message": "The parameter 'school.degrees_awarded.predominant' expects a value of type integer, but received 'frog' which is a value of type string."
    }
  ]
}
```

When failing to execute a query, Open Data Maker will attempt to return a JSON error document that explains the problem. Points to note:

* The HTTP response status will be in the 400 or 500 range, indicating a problem.
* The JSON document contains a single top-level element, `errors`, containing a JSON Array of error objects. Instead of simply returning the first error encountered, Open Data Maker attempts to list all the problems that it detected.
* Error objects always contain these two elements:
    * `error`: a symbolic error code for the specific error that occurred.
    * `message`: an English description of the error.
* Error objects may also contain these fields:
    * `input`: the provided input which triggered the error.
    * `parameter`: the parameter in which the `input` was supplied.
    * `input_type` & expected_type`: In the case of a type mismatch, these fields list the data types that were provided and expected.

## Field Parameters

Parameter names are assumed to be field names in the dataset. Supplying a value to a field parameter acts as a query filter, and only returns records where the given field exactly matches the given value.

For example: Use the parameter `school.region_id=6` to only fetch records with a `school.region_id` value of `6`.

### Word and substring matches on `autocomplete` fields

Certain text fields in the dataset - those with the `autocomplete` data type - allow querying with a list of words. To search for a given word or string of words in those fields, just provide a list of space-separated words. This will return all records where the given field contains the given words as part of a string. **Note that all given words have to be at least three characters long.**

For example: To search for school names containing the words `New York`, use this parameter: `school.name=New%20York` (`%20` is a URL-encoded space) This will match all of these names:

* `New York College of Health Professions`
* `American Academy of Dramatic Arts-New York`
* `School of Professional Horticulture at the New York Botanical Garden`
* `The New College of York` (because the parameter words don't have to be found together)
* `Royal College of New Yorkminster` (because parameter words are matched as parts of other words)

but not this name:

* `New England School of Arts` (because `York` is missing)

### Value Lists

To filter by a set of strings or integers you can provide a comma-separated list of values. This will query the field for an exact match with _any_ of the supplied values.

For example: `school.degrees_awarded.predominant=2,3,4` will match records with a `school.degrees_awarded.predominant` value of `2`, `3` or `4`.

**Note:** Value lists with wildcards or floating-point numbers are not currently supported.

### Negative matches with the `__not` operator

To exclude a set of records from results, use a negative match (also known as an inverted match). Append the characters `__not` to the parameter name to specify a negative match.

For example: `school.region_id__not=5` matches on records where the `school.region_id` does _not_ equal `5`.

### Range matches with the `__range` operator

To match on field values in a particular numeric range, use a range match. Append the characters `__range` to the parameter name to specify a range match, and provide two numbers separated by two periods (`..`).

For example: `2013.student.size__range=100..500` matches on schools which had between 100 and 500 students in 2013.

Open-ended ranges can be performed by omitting one side of the range. For example: `2013.student.size__range=1000..` matches on schools which had over 1000 students.


#### Additional Notes on Ranges

* Both integer and floating-point ranges are supported.
* The left-hand number in a range must be lower than the right-hand number.
* Ranges are inclusive. For example, the range `3..7` matches both `3` and `7`, as well as the numbers in between.

## Option Parameters

You can perform extra refinement and organisation of search results using **option parameters**. These special parameters are listed below.

### Limiting Returned Fields with `fields`

By default, records returned in the query response include all their stored fields. However, you can limit the fields returned with the `fields` option parameter. This parameter takes a comma-separated list of field names. For example: `fields=id,school.name,school.state` will return result records that only contain those three fields.

Requesting specific fields in the response will significantly improve performance and reduce JSON traffic, and is recommended.

### Pagination with `page` and `per_page`

By default, results are returned in pages of 20 records at a time. To retrieve pages after the first, set the `page` option parameter to the number of the page you wish to retrieve. Page numbers start at zero; so, to return records 21 through 40, use `page=1`. Remember that the total number of records available for a given query is given in the `total` field of the top-level `metadata` object.

You can also change the number of records returned per page using the `per_page` option parameter, up to a maximum of 100 records. Bear in mind, however, that large result pages will increase the amount of JSON returned and reduce the performance of the API.

### Sorting with `sort`

To sort results by a given field, use the `sort` option parameter. For example, `sort=2015.student.size` will return records sorted by 2015 student size, in ascending order.

By default, using the `sort` option returns records sorted into ascending order, but you can specify ascending or descending order by appending `:asc` or `:desc` to the field name. For example: `sort=2015.student.size:desc`

**Note:** Sorting is only available on fields with the data type `integer`, `float`, `autocomplete` or `name`.

**Note:** Make sure the sort parameter is a field in the data set. For more information, please take a look at [data dictionary](https://collegescorecard.ed.gov/assets/CollegeScorecardDataDictionary.xlsx)

### Geographic Filtering with `zip` and `distance`

When the dataset includes a `location` at the root level (`location.lat` and
`location.lon`) then the documents will be indexed geographically. You can use the `zip` and `distance` options to narrow query results down to those within a geographic area. For example, `zip=12345&distance=10mi` will return only those results within 10 miles of the center of the given zip code.

Additionally, you can request `location.lat` and `location.lon` in a search that includes a `fields` filter and it will return the record(s) with respective lat and/or lon coordinates.

#### Additional Notes on Geographic Filtering

* By default, any number passed in the `distance` parameter is treated as a number of miles, but you can specify miles or kilometers by appending `mi` or `km` respectively.
* Distances are calculated from the center of the given zip code, not the boundary.
* Only U.S. zip codes are supported.


# New for Version 1.7

With the inclusion of the Department of Education's Field of Study data, there are a number of new improvements that have been incorporated into Open Data Maker.

* The field of study data is included as an array of objects nested under a specified key. These objects may be queried just like any other data. However, there is an additional parameters to add to your API call to manage what is returned. By default, if specifying a search parameter, only objects of the array that match that parameter will be returned. You can pass `&all_programs_nested=true` to return all the items in the array instead of just those that match.
* When specifying specific fields to be returned from the API, the default response is to have a dotted string of the path to the field returned. As of version 1.7, you can pass the parameter `keys_nested=true` get back a true json object instead of the dotted string.
* Lastly, wildcard fields are now possible with version 1.7. If you want to get back data for just the latest available data, it is now possible to specify a query such as `fields=id,school,latest` which will return the ID field, the School object and the Latest object and all the nested objects contained within each. 

# Useful fields:

For a full breakdown of the possible variables (and there are a lot), see the [official documentation](https://collegescorecard.ed.gov/assets/InstitutionDataDocumentation.pdf)

### Summary of some useful ones: 
* INSTNM: The name of the institution
* CITY The city where the institution is located
* STABBR Abbreviation of the state where the institution is located
* REGION A categorical variable describing the region of the institution:

  1 New England (CT, ME, MA, NH, RI, VT)
  2 Mid East (DE, DC, MD, NJ, NY, PA)
  3 Great Lakes (IL, IN, MI, OH, WI)
  4 Plains (IA, KS, MN, MO, NE, ND, SD)
  5 Southeast (AL, AR, FL, GA, KY, LA, MS, NC, SC, TN, VA, WV)
  6 Southwest (AZ, NM, OK, TX)
  7 Rocky Mountains (CO, ID, MT, UT, WY)
  8 Far West (AK, CA, HI, NV, OR, WA)
  9 Outlying Areas (AS, FM, GU, MH, MP, PR, PW, VI)
* LOCALE A categorical variable describing the location of the institution:11 City: Large (population of 250,000 or more)
  
* 12 City: Midsize (population of at least 100,000 but less than 250,000)
  13 City: Small (population less than 100,000)
  21 Suburb: Large (outside principal city, in urbanized area with population of 250,000 or more)
  22 Suburb: Midsize (outside principal city, in urbanized area with population of at least 100,000 but less than 250,000)
  23 Suburb: Small (outside principal city, in urbanized area with population less than 100,000)
  31 Town: Fringe (in urban cluster up to 10 miles from an urbanized area)
  32 Town: Distant (in urban cluster more than 10 miles and up to 35 miles from an urbanized area)
  33 Town: Remote (in urban cluster more than 35 miles from an urbanized area)
  41 Rural: Fringe (rural territory up to 5 miles from an urbanized area or up to 2.5 miles from an urban cluster)
  42 Rural: Distant (rural territory more than 5 miles but up to 25 miles from an urbanized area or more than 2.5 and up to 10 miles from an urban cluster)
  43 Rural: Remote (rural territory more than 25 miles from an urbanized area and more than 10 miles from an urban cluster)
* ADM_RATE Percentage of applicants that are admitted in to the institution
* ACTCMMID Median cumulative ACT score of enrolled students
* ACTENMID Median English ACT subscore of enrolled students
* ACTMTMID Median Math ACT subscore of enrolled students
* PFTFAC Percent of faculty that are full time
* COSTT4_A Average yearly cost of attending
* AVGFACSAL Average faculty salary
* NUMBRANCH: Number of branch campuses
* CONTROL: This element is reported directly to IPEDS, and identifies whether the institution’s governance structure is public, private nonprofit, or private for-profit.
* ADM_RATE_ALL: Colleges report to IPEDS their Fall admissions rate, defined as the number of admitted undergraduates divided by the number of undergraduates who applied. ADM_RATE_ALL represents the admissions rate across all campuses, defined as the total number of admitted undergraduates across all branches divided by the total number of undergraduates who applied across all branches.
* AVGFACSAL:
  The average faculty salary produces the average faculty salary per month, by dividing the total salary outlays by the number of months worked for all full-time, nonmedical instructional staff.
* TUITFTE:
  The net tuition revenue per full-time equivalent (FTE) student uses tuition revenue minus discounts and allowances, and divides that by the number of FTE undergraduate and graduate students.
* DISTANCEONLY:
  Institutions are identified as distance education-only if all their programs are available only via distance education.
* SATVR25, SATVR75, SATMT25, SATMT75, ACTCM25, ACTCM75:
  The files include the 25th and 75th percentiles of SAT reading (SATVR* for _25 and _75), writing (SATWR* for _25 and _75), math (SATMT* for _25 and _75)
  NOTE: Data for SATWR is absent and the corresponding columns should be dropped.
* UGDS:
  This element includes the number of degree/certificate-seeking undergraduates enrolled in the fall, as reported in the IPEDS Fall Enrollment component.
* PCTFLOAN:
  This element, as reported in the IPEDS Student Financial Aid (SFA) component, shows the share of undergraduate students who received federal loans in a given year.
* CDR3:
  Cohort default rates are produced annually as an institutional accountability metric; institutions with high default rates may lose access to federal financial aid. The three-year cohort default rate (CDR3) represents a snapshot in time.
* URL: Each institution reports the URL of its homepage (INSTURL) and the URL
  of its net price calculator (NPCURL).
* MAIN: The main campus column (MAIN4
  ) identifies whether the institution’s
  IPEDS-derived data elements represent the main campus of the
  institution or not, where 1 is a main campus and 0 is not

### Academic Areas offered: Academic Areas Offered Integer
The Classification of Instructional Programs (CIP) provides a taxonomy
for different academic disciplines. Two types of CIP data are included in
these institution-level data files. The first set (PCIP[01-54]) provide the
percentage of degrees awarded in each two-digit CIP code category of
academic areas. The second set [CERT1/CERT2/ASSOC/BACHL/CERT4]) identifies which academic
areas are offered at which levels and whether or not specific offerings
are available through distance education. 13 These CIP elements within
each Scorecard data file are derived from the IPEDS Completions
component. Reported awards cover the 12-month period ending June
30 prior to the IPEDS collection year. The College Scorecard data files
that provide information about specific fields of study within institutions
offer a more granular picture (at the four-digit CIP code level) of what
fields of study are available at each institution. For more information on
these data, please see the [technical documentation] (https://collegescorecard.ed.gov/assets/FieldOfStudyDataDocumentation.pdf) for field of study
data files.

### Aknowledgements:
Some of this is based off the helpful [documentation](https://github.com/RTICWDT/open-data-maker/blob/master/API.md) retrieved from Open Data Maker by RTICWDT.
Other resources: 
[Official documentation provided by the Departement of Education](https://collegescorecard.ed.gov/data/documentation/)
[rscorecard docmentation](https://cran.r-project.org/web/packages/rscorecard/rscorecard.pdf)
[US score card analysis project](https://www.kaggle.com/code/hassanshahin/us-colleges-scorecard-analysis) by Hassan Shahir
[College data dictionary](https://remiller1450.github.io/s230s19/CollegeDataDictionary.html) by R.E Miller


Author: 
Henry "TJ" Chen
