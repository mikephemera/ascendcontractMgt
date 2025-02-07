import os
import re


def process_java_file(file_path):
    """处理单个Java文件，返回带注释的合并内容"""
    with open(file_path, "r", encoding="utf-8") as f:
        content = f.read()

    # 提取包名和类名
    package_match = re.search(r"^package\s+([\w\.]+);", content, re.MULTILINE)
    class_name = os.path.splitext(os.path.basename(file_path))[0]

    # 生成文件路径注释
    file_path_comment = ""
    file_path_comment = f"{class_name}.java"

    # 移除所有import语句
    processed_content = re.sub(r"^import\s+.*?;\s*$", "", content, flags=re.MULTILINE)

    # 清理多余空行并添加文件标记
    processed_content = re.sub(r"\n{3,}", "\n\n", processed_content.strip())
    return (
        f"// {file_path_comment}\n"
        f"{processed_content}\n"
        f"// FILE_END \n"
    )


def merge_java_files(folder_path):
    """合并指定文件夹下的所有Java文件"""
    output_filename = f"{os.path.basename(folder_path)}.txt"
    java_files = [
        f
        for f in os.listdir(folder_path)
        if f.endswith(".java") and os.path.isfile(os.path.join(folder_path, f))
    ]

    merged_content = []
    for java_file in sorted(java_files):
        full_path = os.path.join(folder_path, java_file)
        merged_content.append(process_java_file(full_path))

    # 写入合并文件
    with open(output_filename, "w", encoding="utf-8") as f:
        f.write("\n".join(merged_content))

    print(f"成功合并 {len(java_files)} 个文件到 {output_filename}")


if __name__ == "__main__":
    target_folder = input("请输入包含Java文件的文件夹路径：").strip()
    if os.path.isdir(target_folder):
        merge_java_files(target_folder)
    else:
        print("错误：指定的路径不是有效目录")
