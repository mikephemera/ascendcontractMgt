import os
import re


def split_java_files(content):

    output_dir = "input"
    if not os.path.exists(output_dir):
        os.makedirs(output_dir)

    # 使用正则表达式匹配Java文件内容
    # 匹配以//开头的文件名注释，后面跟着package声明直到下一个文件开始
    pattern = r"//\s*([\w]+\.java)\s*\n(.*?)(?=//\s*[\w]+\.java|\Z)"
    matches = re.findall(pattern, content, re.DOTALL)

    # 遍历所有匹配项并保存文件
    for filename, content in matches:
        # 构建完整的文件路径
        file_path = os.path.join(output_dir, filename)

        # 保存文件
        with open(file_path, "w", encoding="utf-8") as f:
            # 去除开头的空行，但保留其他格式
            file_content = content.strip()
            f.write(file_content)

        print(f"已保存: {filename}")


def main():
    # 从input.txt读取内容
    try:
        with open("input.txt", "r", encoding="utf-8") as file:
            content = file.read()
    except FileNotFoundError:
        print("错误: 找不到 input.txt 文件")
        return
    except Exception as e:
        print(f"读取文件时发生错误: {e}")
        return
        # 处理文件分割
    split_java_files(content)
    print("\n所有文件已保存到 'input' 目录")


if __name__ == "__main__":
    main()
