public:
    string countAndSay(int n) {
        string curr("1");
        string prev;

        for (int i = 1; i < n; i++) {
            prev = curr;
            curr = "";

            char say = prev[0];
            int count  = 1;

            for (int j = 1; j < prev.length(); j++) {
                if (prev[j] == say) {
                    count++;
                } else {
                    curr += to_string(count);
                    curr += say;

                    say = prev[j];
                    count = 1;
                }
            }

            curr += to_string(count);
            curr += say;
        }

        return curr;
    }