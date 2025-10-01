ANSI_ESCAPE = "\033["
COLOR_RED = "31m"
COLOR_GREEN = "32m"
RESET = "\033[0m"


def main():
    user_input = ""
    while user_input != "q":
        user_input = input("Enter top limit int number: ")

        top_limit = 0
        try:
            top_limit = int(user_input)
            if top_limit < 0:
                raise ValueError
        except ValueError:
            print(
                f"{ANSI_ESCAPE}{COLOR_RED}Incorrect input!",
                f"Must be positive int!{RESET}",
            )
            continue

        print("Below all perfect numbers below the limit: ")
        print(get_perfect_numbers_up_to(top_limit))


def get_perfect_numbers_up_to(limit: int) -> list[int]:
    if isinstance(limit, str) or isinstance(limit, float):
        raise TypeError
    if limit < 0:
        raise ValueError

    perfect_numbers: list[int] = []
    p = 2
    while True:
        try:
            perfect_number = get_perfect_number(p)
            if perfect_number > limit:
                break
            if perfect_number:
                perfect_numbers.append(perfect_number)
        except ValueError:
            pass

        p = next_prime(p)
    return perfect_numbers


def get_perfect_number(p: int) -> int:
    if isinstance(p, str) or isinstance(p, float):
        raise TypeError
    if p <= 0:
        raise ValueError

    if lucas_lehmer(p):
        return (1 << (p - 1)) * get_merssene_number(p)

    raise ValueError(
        "Can't get perfect number p didn't pass lucas lehmer test"
    )


def lucas_lehmer(p: int) -> bool:
    if isinstance(p, float) or isinstance(p, str):
        raise TypeError
    if p < 2:
        raise ValueError

    if p == 2:
        return True
    M = get_merssene_number(p)
    s = 4
    for _ in range(p - 2):
        s = (s**2 - 2) % M
    return s == 0


def get_merssene_number(p: int) -> int:
    if isinstance(p, str) or isinstance(p, float):
        raise TypeError
    if p <= 0:
        raise ValueError

    return (1 << p) - 1


def next_prime(num: int) -> int:
    candidate = num + 1
    while True:
        if is_prime(candidate):
            return candidate
        candidate += 1


def is_prime(num: int) -> int:
    if num < 2:
        return False
    if num % 2 == 0:
        return False
    if num == 2:
        return True
    for i in range(3, int(num**0.5) + 1, 2):
        if num % i == 0:
            return False
    return True


if __name__ == "__main__":
    main()
