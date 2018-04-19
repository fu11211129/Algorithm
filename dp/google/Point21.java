// 牌只有1-10点，如果还没满17点就要再拿一张，问超过21点的机率是多少?
// DP问题，但面试时太紧张，不知道怎么设定DP，尤其是被一直抽牌有好几轮迷惑了。
// 最后面试 官提示，只要用DP计算拿到我点的机率，然后1-(P(17 )+ P(18)+ P(19)+ P(20)+ P (21))就好，还是太浅了我

public class Point21 {
    public static void main(String[] args) {
        // define f[i] as the probability of get total value i
        double[] f = new double[21 + 1];

        f[0] = 1.0/10.0;

        // only 1 makes up 1
        f[1] = 1.0 / 10.0;

        // (1, 1), (2) make up 2
        // f[2] = (1.0/10.0) * (1.0/10.0) + 1.0/10.0;
        // f[2] = (f[0] + f[1])/10.0

        // (1,1,1), (1, 2), (3) makes up 3
        // f[3] = (1/10.0) * (1/10.0) * (1/10.0) + (1/10.0) * (1/10.0) + 1/10.0
        // f[3] = (f[0] + f[1] + f[2]) / 10.0

        for(int i=2; i<=10; ++i) {
            double s = 0.0;
            for(int j=0; j<=i-1; ++j) {
                s += f[j];
            }
            f[i] = s / 10.0;
        }

        // 11 = (1 + 10), (2 + 9), (3 + 8), (4 + 7), (5 + 6)
        for(int i=11; i<=21; ++i) {
            // f[i] = f[i-1] + f[i-10] + f[i-2] + f[i-9] + ... + f[i-6] + f[i-5]
            double s = 0.0;
            for(int j=i-10; j<=i-1; ++j) {
                s += f[j];
            }
            f[i] = s / 10.0;
        }

        double result = 1 - (f[17] + f[18] + f[19] + f[20] + f[21]);
    }
}
