# 抖码算法，让算法学习变得简单有趣
# 作者：老汤

if __name__ == '__main__':
    # issue 讨论：https://gitee.com/douma_edu/douma_algo_training_camp/issues/I4BZ4A
    # 第一行是切的刀数
    n = int(input())

    # longitudes 记录经度的度数是否被切(经度作为数组的索引)，就是竖着切
    # latitudes 记录纬度的度数是否被切(纬度作为数组的索引)，就是横着切
    longitudes, latitudes = [False] * 365, [False] * 365

    # 分别记录竖着切和横着切的次数
    lng_cnt, lat_cnt = 0, 0

    # 接下来是 n 刀切的情况
    for i in range(n):
        data = str(input()).split(" ")
        if data[0] == "1":
            lng = int(data[1])
            if not longitudes[lng]:
                lng_cnt += 1
                longitudes[lng] = True
        elif data[0] == "0":
            lat = int(data[1])
            if not latitudes[lat]:
                lat_cnt += 1
                latitudes[lat] = True

    if lng_cnt == 0:
        print(str(lat_cnt + 1))
    else:
        print(str((lng_cnt * 2) * (lat_cnt + 1)))