# https://leetcode.com/problems/queries-quality-and-percentage

# Calculate the 'quality' column
with avg_quality_table as (
    select 
        query_name,
        round(avg(rating / position), 2) as quality
    from queries
    group by query_name
),
# Calculate the 'poor_query_percentage' column
poor_query_percentage_table as (
    with totals as (
        select
            query_name, 
            count(rating) as total 
        from queries
        group by query_name
    )
    select 
        poor.query_name,  
        round(count(rating) * 100 / total, 2) as poor_query_percentage 
    from queries poor
    left join totals on totals.query_name = poor.query_name
    where rating < 3
    group by poor.query_name
)
# Join them together, handling null values
select a.query_name, a.quality, ifnull(q.poor_query_percentage, 0) as poor_query_percentage 
from avg_quality_table a
left join poor_query_percentage_table q on q.query_name = a.query_name
where a.query_name is not null


# My solution after seeing other solutions
select
    query_name, 
    round(
        avg(rating / position), 
        2
    ) as quality,
    round(
        sum(
            case 
                when rating < 3 then 1
                else 0
            end
        ) / count(rating) * 100, 
        2
    ) as poor_query_percentage
from queries
where query_name is not null
group by query_name
