float
avg_array(float *array, int num_entries)
{
    int i;
    float sum = 0.0f;

    for (i = 0; i < num_entries; i++)
    {
        sum += array[i];
    }
    return sum / num_entries;
}