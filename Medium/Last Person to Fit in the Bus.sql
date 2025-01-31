select person_name
from (
    select 
        person_name,
        sum(weight) over (order by turn) as accumulated_weight  # Window function
    from queue
) accuulated_weights_table
where accumulated_weight <= 1000
order by accumulated_weight desc
limit 1
