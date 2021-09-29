/**
 * Note: The returned array must be malloced, assume caller calls free().
 */
int* findDuplicates(int* nums, int numsSize, int* returnSize){
    *returnSize = 0;

    if ((nums == NULL) || (numsSize == 0)) {
        *returnSize = 0;
        return NULL;
    }

    int* count = (int*)malloc(sizeof(int) * numsSize);
    int* pret = (int*)malloc(sizeof(int) * numsSize);

    if (count == NULL) {
        *returnSize = 0;
        return NULL;
    }

    memset(count, 0, sizeof(int) * numsSize);

    for (int i = 0; i < numsSize; i++) {
        count[(nums[i] - 1)]++;
    }


    for (int i = 0; i < numsSize; i++) {
        if (2 == count[i]) {
            pret[*returnSize] = i + 1;
            // 这里的指针自增需要注意，要有括号
            // 详见解释：https://www.cnblogs.com/biem/p/13510476.html
            (*returnSize)++;
        }
    }

    return pret;
}